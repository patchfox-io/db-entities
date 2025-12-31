package io.patchfox.db_entities.entities;


import java.time.ZonedDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;


@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
@ToString
public class DatasourceMetrics {

    //

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)  
    private long datasourceEventCount;

    @Column(nullable = false)  
    private ZonedDateTime commitDateTime;

    @Column(nullable = false)  
    private ZonedDateTime eventDateTime;

    @Column(nullable = false)
    private UUID txid;

    @Column(nullable = false)
    private UUID jobId;

    //

    @Column(nullable = false)
    private String purl;

    //

    @Column()
    private long totalFindings;

    @Column()
    private long criticalFindings;

    @Column()
    private long highFindings;

    @Column()
    private long mediumFindings;

    @Column()
    private long lowFindings;

    //

    @Column()
    private long findingsAvoidedByPatchingPastYear;

    @Column()
    private long criticalFindingsAvoidedByPatchingPastYear;

    @Column()
    private long highFindingsAvoidedByPatchingPastYear;

    @Column()
    private long mediumFindingsAvoidedByPatchingPastYear;

    @Column()
    private long lowFindingsAvoidedByPatchingPastYear;

    //

    @Column()
    private double findingsInBacklogBetweenThirtyAndSixtyDays;

    @Column()
    private double criticalFindingsInBacklogBetweenThirtyAndSixtyDays;

    @Column()
    private double highFindingsInBacklogBetweenThirtyAndSixtyDays;

    @Column()
    private double mediumFindingsInBacklogBetweenThirtyAndSixtyDays;

    @Column()
    private double lowFindingsInBacklogBetweenThirtyAndSixtyDays;

    //

    @Column()
    private double findingsInBacklogBetweenSixtyAndNinetyDays;

    @Column()
    private double criticalFindingsInBacklogBetweenSixtyAndNinetyDays;

    @Column()
    private double highFindingsInBacklogBetweenSixtyAndNinetyDays;

    @Column()
    private double mediumFindingsInBacklogBetweenSixtyAndNinetyDays;

    @Column()
    private double lowFindingsInBacklogBetweenSixtyAndNinetyDays;

    //

    @Column()
    private double findingsInBacklogOverNinetyDays;

    @Column()
    private double criticalFindingsInBacklogOverNinetyDays;
    
    @Column()
    private double highFindingsInBacklogOverNinetyDays;

    @Column()
    private double mediumFindingsInBacklogOverNinetyDays;

    @Column()
    private double lowFindingsInBacklogOverNinetyDays;

    //

    @Column()
    private long packages;

    @Column()
    private long packagesWithFindings;

    @Column()
    private long packagesWithCriticalFindings;

    @Column()
    private long packagesWithHighFindings;

    @Column()
    private long packagesWithMediumFindings;

    @Column()
    private long packagesWithLowFindings;

    //

    @Column()
    private long downlevelPackages;

    @Column()
    private long downlevelPackagesMajor;

    @Column()
    private long downlevelPackagesMinor;

    @Column()
    private long downlevelPackagesPatch;

    //

    @Column()
    private long stalePackages;

    @Column()
    private long stalePackagesSixMonths;

    @Column()
    private long stalePackagesOneYear;

    @Column()
    private long stalePackagesOneYearSixMonths;

    @Column()
    private long stalePackagesTwoYears;

    //

    @Column()
    private long patches;

    @Column()
    private long samePatches;

    @Column()
    private long differentPatches;

    @Column()
    private long patchFoxPatches;

    //

    @Column()
    private double patchEfficacyScore;

    @Column()
    private double patchImpact;

    @Column
    private double patchEffort;

    //

}
