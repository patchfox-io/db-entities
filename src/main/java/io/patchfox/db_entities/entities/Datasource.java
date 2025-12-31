package io.patchfox.db_entities.entities;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Set;
import java.util.UUID;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.HashSet;
import java.util.List;

import com.github.packageurl.MalformedPackageURLException;
import com.github.packageurl.PackageURL;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
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
// Serializable is there to prevent error JdbcTypeRecommendationException: Could not determine recommended JdbcType
public class Datasource implements Serializable {

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "datasource", cascade = CascadeType.ALL)
    @Column()
    private Set<Edit> edits;

    // @OneToMany(mappedBy="datasource", cascade = CascadeType.ALL)
    // @Column(nullable = false)
    // @Builder.Default
    // @Fetch(FetchMode.JOIN)
    // private Set<DatasourceEvent> datasourceEvents = new HashSet<>();

    //

    // @ManyToMany(mappedBy = "datasources", cascade = CascadeType.ALL)
    // @Builder.Default
    // @Fetch(FetchMode.JOIN)
    // private Set<Package> packages = new HashSet<>();

    // @ManyToMany(cascade = CascadeType.ALL)
    // @JoinTable(
    //     name = "datasource_dataset",
    //     joinColumns = { @JoinColumn(name = "datasource_id") },
    //     inverseJoinColumns = { @JoinColumn(name = "dataset_id") }
    // )
    // @Builder.Default
    // @Fetch(FetchMode.JOIN)
    @ManyToMany(
        cascade = {CascadeType.PERSIST, CascadeType.MERGE},
        fetch = FetchType.LAZY
    )
    @JoinTable(
        name = "datasource_dataset",
        joinColumns = {@JoinColumn(name = "datasource_id")},
        inverseJoinColumns = {@JoinColumn(name = "dataset_id")}
    )
    @Builder.Default
    private Set<Dataset> datasets = new HashSet<>();

    //

    public enum Status { 
        INITIALIZING, // I am new and ingesting historical data and am not ready to be processed
        INGESTING, // I'm ingesting new data and am not ready to be processed
        READY_FOR_PROCESSING, // I have data to be processed
        READY_FOR_NEXT_PROCESSING, // I am ready for the next stage of processing
        PROCESSING, // my data is being processed
        PROCESSING_ERROR, // error occured during last processing attempt
        IDLE // I got nothing going on
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private UUID latestTxid;

    @Column()
    private UUID latestJobId;

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


    /*
     * The purl format looks like this:
     *      scheme:type/namespace/name@version?qualifiers#subpath
     * 
     * a PatchFox Datasource purl looks like: 
     *      pkg:generic/{DOMAIN}/{DATASOURCE}@{DATASOURCE_TYPE}
     * 
     * for a git Datasource the purl looks like: 
     *      pkg:generic/{DOMAIN}/{DATASOURCE}::{COMMIT_BRANCH}@{DATASOURCE_TYPE}    
     */


    //
    @Column(nullable = false)
    private String domain;

    @Column(nullable = false)
    private String name;

    // can be nullable because not all datasources represent git repositories 
    @Column()
    private String commitBranch;

    // purl type (maven/npm/pypi/...) / some oss tool / etc... 
    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private double numberEventsReceived;

    @Column(nullable = false)
    private double numberEventProcessingErrors;

    @Column(nullable = false)  
    private ZonedDateTime firstEventReceivedAt;
    // @Setter(AccessLevel.NONE)
    // private String firstEventReceivedAt;
    // public void setFirstEventReceivedAt(String firstEventReceivedAt) {
    //     try {
    //         ZonedDateTime.parse(firstEventReceivedAt);
    //     } catch (DateTimeParseException e) {
    //         log.error("refusing to set {} as it's invalid as a datetime string");
    //         throw new IllegalArgumentException();
    //     }
    //     this.firstEventReceivedAt = firstEventReceivedAt;
    // }

    @Column(nullable = false)  
    private ZonedDateTime lastEventReceivedAt;
    // @Setter(AccessLevel.NONE)
    // private String lastEventReceivedAt;
    // public void setLastEventReceivedAt(String lastEventReceivedAt) {
    //     try {
    //         ZonedDateTime.parse(lastEventReceivedAt);
    //     } catch (DateTimeParseException e) {
    //         log.error("refusing to set {} as it's invalid as a datetime string");
    //         throw new IllegalArgumentException();
    //     }
    //     this.lastEventReceivedAt = lastEventReceivedAt;
    // }
    
    @Column(nullable = false)
    private String lastEventReceivedStatus;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Column(name = "package_indexes")
    private List<Long> packageIndexes;


    // public static class DatasourceBuilder {

    //     String firstEventReceivedAt;

    //     String lastEventReceivedAt;


    //     public DatasourceBuilder firstEventReceivedAt(String firstEventReceivedAt) {
    //         try {
    //             ZonedDateTime.parse(firstEventReceivedAt);
    //         } catch (DateTimeParseException e) {
    //             log.error("refusing to set {} as it's invalid as a datetime string");
    //             throw new IllegalArgumentException();
    //         }

    //         this.firstEventReceivedAt = firstEventReceivedAt;
    //         return this;
    //     }
    

    //     public DatasourceBuilder lastEventReceivedAt(String lastEventReceivedAt) {
    //         try {
    //             ZonedDateTime.parse(lastEventReceivedAt);
    //         } catch (DateTimeParseException e) {
    //             log.error("refusing to set {} as it's invalid as a datetime string");
    //             throw new IllegalArgumentException();
    //         }

    //         this.lastEventReceivedAt = lastEventReceivedAt;
    //         return this;
    //     }

    // }

}
