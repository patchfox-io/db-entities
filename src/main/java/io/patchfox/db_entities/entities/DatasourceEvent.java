package io.patchfox.db_entities.entities;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;
import java.util.Set;
import java.util.HashSet;
import java.util.UUID;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.github.packageurl.MalformedPackageURLException;
import com.github.packageurl.PackageURL;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;


@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class DatasourceEvent {

    @ManyToOne()
    @JoinColumn(nullable = false, name = "datasource_id")
    private Datasource datasource;

    //

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "datasource_event_package",
        joinColumns = { @JoinColumn(name = "datasource_event_id") },
        inverseJoinColumns = { @JoinColumn(name = "package_id") }
    )
    @Builder.Default
    private Set<Package> packages = new HashSet<>();

    // @ManyToMany(cascade = CascadeType.ALL)
    // @JoinTable(
    //     name = "datasource_event_dataset",
    //     joinColumns = { @JoinColumn(name = "datasource_event_id") },
    //     inverseJoinColumns = { @JoinColumn(name = "dataset_id") }
    // )
    // @Builder.Default
    // @Fetch(FetchMode.JOIN)
    // private Set<Dataset> datasets = new HashSet<>();

    //

    public enum Status {
        INGESTING, // I'm ingesting new data and am not ready to be processed
        READY_FOR_PROCESSING, // I have new data to be processed
        READY_FOR_NEXT_PROCESSING, // I am ready for the next stage of processing
        PROCESSING, // my data is being processed
        PROCESSED, // my data was successfully processed
        PROCESSING_ERROR, // error occured during last processing attempt
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter(AccessLevel.NONE)
    @Column(nullable = false, unique = true)
    private String purl;

    public void setPurl(String purl) {
        try {
            this.purl = new PackageURL(purl).toString();
        } catch (MalformedPackageURLException e) {
            log.error("{} is not a valid PackageURL", purl);
        }
    }

    @Column(nullable = false, unique = true)
    private UUID txid;

    @Column()
    private UUID jobId;

    @Column()
    private String commitHash;

    @Column()
    private String commitBranch;

    //
    // doing this weird stuff with datetime because if we don't the front end ends up with values like 
    // 
    // "firstEventReceivedAt": 1724888528.002862
    // "lastEventReceivedAt": 1724890185.065798
    //

    @Column()
    private ZonedDateTime commitDateTime;
    // @Setter(AccessLevel.NONE)
    // private String commitDateTime;
    // public void setCommitDateTime(String commitDateTime) {
    //     try {
    //         ZonedDateTime.parse(commitDateTime);
    //     } catch (DateTimeParseException e) {
    //         log.error("refusing to set {} as it's invalid as a datetime string");
    //         throw new IllegalArgumentException();
    //     }
    // }

    // public void setCommitDateTime(ZonedDateTime commitDateTime) {
    //     this.commitDateTime = commitDateTime.toString();
    // }


    @Column(nullable = false)
    private ZonedDateTime eventDateTime;
    // @Setter(AccessLevel.NONE)
    // private String eventDateTime;
    // public void setEventDateTime(String commitDateTime) {
    //     try {
    //         ZonedDateTime.parse(commitDateTime);
    //     } catch (DateTimeParseException e) {
    //         log.error("refusing to set {} as it's invalid as a datetime string");
    //         throw new IllegalArgumentException();
    //     }
    // }

    // public void setEventDateTime(ZonedDateTime commitDateTime) {
    //     this.commitDateTime = commitDateTime.toString();
    // }    


    @Column(nullable = false)
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private byte[] payload;

    public boolean isPayloadNull() { return payload == null; }

    public byte[] getPayload() throws IOException, DataFormatException {
        Inflater inflater = new Inflater();
        inflater.setInput(this.payload);

        // bytearrayoutput stream technically does not need to be closed but we're doing it anyway 
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[1024];

            while (!inflater.finished()) {
                int decompressedSize = inflater.inflate(buffer);
                outputStream.write(buffer, 0, decompressedSize);
            }
    
            inflater.end(); // frees resources
            return outputStream.toByteArray();
        }

    }

    public void setPayload(byte[] payload) throws IOException {
        Deflater deflater = new Deflater();
        deflater.setInput(payload);
        deflater.finish();

        // bytearrayoutput stream technically does not need to be closed but we're doing it anyway 
        try(ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[1024];

            while (!deflater.finished()) {
                int compressedSize = deflater.deflate(buffer);
                outputStream.write(buffer, 0, compressedSize);
            }
    
            deflater.end(); // frees resources
            this.payload = outputStream.toByteArray();
        }

    }


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Column()
    private String processingError;

    @Column()
    @Builder.Default
    private boolean ossEnriched = false;

    @Column()
    @Builder.Default
    private boolean packageIndexEnriched = false;

    @Column()
    @Builder.Default
    private boolean analyzed = false;

    @Column()
    @Builder.Default
    private boolean forecasted = false;

    @Column()
    @Builder.Default
    private boolean recommended = false;
    
    
    public static class DatasourceEventBuilder {

        byte[] payload;

        // String eventDateTime;

        // String commitDateTime;

        public DatasourceEventBuilder payload(byte[] payload) {
            Deflater deflater = new Deflater();
            deflater.setInput(payload);
            deflater.finish();
    
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
    
            while (!deflater.finished()) {
                int compressedSize = deflater.deflate(buffer);
                outputStream.write(buffer, 0, compressedSize);
            }
    
            this.payload = outputStream.toByteArray();
            return this;
        }


        // public DatasourceEventBuilder eventDateTime(String eventDateTime) {
        //     try {
        //         ZonedDateTime.parse(eventDateTime);
        //     } catch (DateTimeParseException e) {
        //         log.error("refusing to set {} as it's invalid as a datetime string");
        //         throw new IllegalArgumentException();
        //     }

        //     this.eventDateTime = eventDateTime;
        //     return this;
        // }
    

        // public DatasourceEventBuilder commitDateTime(String commitDateTime) {
        //     try {
        //         ZonedDateTime.parse(commitDateTime);
        //     } catch (DateTimeParseException e) {
        //         log.error("refusing to set {} as it's invalid as a datetime string");
        //         throw new IllegalArgumentException();
        //     }

        //     this.commitDateTime = commitDateTime;
        //     return this;
        // }
    }


}
