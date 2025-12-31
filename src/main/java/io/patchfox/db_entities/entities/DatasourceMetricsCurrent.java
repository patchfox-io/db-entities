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
public class DatasourceMetricsCurrent {

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

    @Column(nullable = false, unique = true)
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

}
