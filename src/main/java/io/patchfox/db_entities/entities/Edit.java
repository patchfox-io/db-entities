package io.patchfox.db_entities.entities;

import java.time.ZonedDateTime;

import com.github.packageurl.MalformedPackageURLException;
import com.github.packageurl.PackageURL;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

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
public class Edit {

    @ManyToOne()
    @JoinColumn(name = "dataset_metrics_id")
    private DatasetMetrics datasetMetrics;

    @ManyToOne()
    @JoinColumn(nullable = false, name = "datasource_id")
    private Datasource datasource;

    //

    public enum EditType { CREATE, UPDATE, DELETE };

    //

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)  
    private ZonedDateTime commitDateTime;

    @Column(nullable = false)  
    private ZonedDateTime eventDateTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EditType editType;

    @Setter(AccessLevel.NONE)
    @Column(nullable = false)
    private String before;
    public void setBefore(String before) {
        try {
            this.before = new PackageURL(before).toString();
        } catch (MalformedPackageURLException e) {
            log.error("{} is not a valid PackageURL", before);
        }
    }

    @Setter(AccessLevel.NONE)
    @Column(nullable = false)
    private String after;
    public void setAfter(String after) {
        try {
            this.after = new PackageURL(after).toString();
        } catch (MalformedPackageURLException e) {
            log.error("{} is not a valid PackageURL", after);
        }
    }

    @Column()
    private boolean isSameEdit;

    @Column()
    private int sameEditCount;

    @Column()
    private boolean isPfRecommendedEdit;

    @Column()
    private boolean isUserEdit;

    @Column()
    private Integer criticalFindings;

    @Column()
    private Integer highFindings;

    @Column()
    private Integer mediumFindings;

    @Column()
    private Integer lowFindings;

    //

    @Column()
    private Integer reduceCvesIndex;

    @Column()
    private Integer reduceCveGrowthIndex;

    @Column()
    private Integer reduceCveBacklogIndex;

    @Column()
    private Integer reduceCveBacklogGrowthIndex;

    @Column()
    private Integer reduceStalePackagesIndex;

    @Column()
    private Integer reduceStalePackagesGrowthIndex;

    @Column()
    private Integer reduceDownlevelPackagesIndex;

    @Column()
    private Integer reduceDownlevelPackagesGrowthIndex;

    @Column()
    private Integer growPatchEfficacyIndex;

    @Column()
    private Integer removeRedundantPackagesIndex;

    //

    @Column()
    private Integer decreaseBacklogRank;

    @Column()
    private Integer decreaseVulnerabilityCountRank;

    @Column() 
    private Integer avoidsVulnerabilitiesRank;

    @Column()
    private Integer increaseImpactRank;

    //
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((editType == null) ? 0 : editType.hashCode());
        result = prime * result + ((datasource == null) ? 0 : datasource.hashCode());
        result = prime * result + ((before == null) ? 0 : before.hashCode());
        result = prime * result + ((after == null) ? 0 : after.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Edit other = (Edit) obj;
        if (editType != other.editType) {
            return false;
        }
        if (datasource == null) {
            if (other.datasource != null) { 
                return false;
            }
        } else if (!datasource.equals(other.datasource)) {
            return false;
        }
        if (before == null) {
            if (other.before != null) {
                return false;
            }
        } else if (!before.equals(other.before)) {
            return false;
        }
        if (after == null) {
            if (other.after != null) {
                return false;
            }
        } else if (!after.equals(other.after)) {
            return false;
        }
        return true;
    }

    
}
