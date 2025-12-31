package io.patchfox.db_entities.entities;


import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Set;
import java.util.UUID;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.HashSet;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class Dataset implements Serializable {

    public enum Status { 
        INITIALIZING, // I am new and ingesting historical data and am not ready to be processed
        INGESTING, // I have new data but it's not ready for pipeline processing yet
        READY_FOR_PROCESSING, // I have data to be processed
        PROCESSING, // my data is being processed
        PROCESSING_ERROR, // error occured during last processing attempt
        IDLE // I got nothing going on
    }

    // @OneToMany(fetch = FetchType.LAZY, mappedBy = "dataset", cascade = CascadeType.ALL)
    // @Builder.Default
    // private Set<Recommendation> datasetRecommendations = new HashSet<>();

    // @OneToMany(fetch = FetchType.LAZY, mappedBy = "dataset", cascade = CascadeType.ALL)
    // @Builder.Default
    // private Set<DatasetMetrics> datasetMetrics = new HashSet<>();

    //

    // @ManyToMany(mappedBy = "datasets", cascade = CascadeType.ALL)
    // @Builder.Default
    // @Fetch(FetchMode.JOIN)
    @ManyToMany(
        mappedBy = "datasets",
        cascade = {CascadeType.PERSIST, CascadeType.MERGE}, // More selective cascading
        fetch = FetchType.LAZY // Change to lazy loading
    )
    @Builder.Default
    private Set<Datasource> datasources = new HashSet<>();

    // @ManyToMany(mappedBy = "datasets", cascade = CascadeType.ALL)
    // @Builder.Default
    // @Fetch(FetchMode.JOIN)
    // private Set<DatasourceEvent> datasourceEvents = new HashSet<>();

    //

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private UUID latestTxid;

    @Column()
    private UUID latestJobId;

    @Setter(AccessLevel.NONE)
    @Column(nullable = false, unique = true)
    private String name;

    public void setName(String datasetName) {
        this.name = datasetName.toUpperCase();
    }


    @Column(nullable = false)  
    private ZonedDateTime updatedAt;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    //

    // /**
    //  * 
    //  * 
    //  * @return
    //  */
    // public List<DatasourceEvent> getEventsToProcess() {

    //     // sort in ascending order by date
    //     List<DatasourceEvent> sortedByDate = datasourceEvents.stream()
    //                                                          .sorted(
    //                                                              (dse1, dse2) -> dse1.getCommitDateTime()
    //                                                                                  .compareTo(dse2.getCommitDateTime())
    //                                                          )
    //                                                          .toList();

    //     // find and return a list starting with the first DatasourceEvent (by commit date) that needs to be processed
    //     for (int i = 0; i < sortedByDate.size(); i ++) {
    //         if (sortedByDate.get(i).getStatus() == DatasourceEvent.Status.READY_FOR_PROCESSING) {
    //             return sortedByDate.subList(i, sortedByDate.size());
    //         }
    //     }

    //     return new ArrayList<DatasourceEvent>();
    // }

}
