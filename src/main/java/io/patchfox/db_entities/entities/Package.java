package io.patchfox.db_entities.entities;


import java.time.ZonedDateTime;
import java.util.Set;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.TimeZoneStorage;
import org.hibernate.annotations.TimeZoneStorageType;

import java.util.HashSet;

import com.github.packageurl.MalformedPackageURLException;
import com.github.packageurl.PackageURL;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
public class Package {

    // Original findings relationship - keep for backward compatibility
    @ManyToMany()
    @JoinTable(
        name = "package_finding",
        joinColumns = { @JoinColumn(name = "package_id") },
        inverseJoinColumns = { @JoinColumn(name = "finding_id") }
    )
    @Builder.Default
    @Fetch(FetchMode.JOIN)
    private Set<Finding> findings = new HashSet<>();

    @ManyToMany
    @JoinTable(
    name = "package_critical_finding",
    joinColumns = {@JoinColumn(name = "package_id")},
    inverseJoinColumns = {@JoinColumn(name = "finding_id")}
    )
    @Fetch(FetchMode.SELECT)
    @org.hibernate.annotations.Immutable
    private Set<Finding> criticalFindings;

    @ManyToMany
    @JoinTable(
    name = "package_high_finding",
    joinColumns = {@JoinColumn(name = "package_id")},
    inverseJoinColumns = {@JoinColumn(name = "finding_id")}
    )
    @Fetch(FetchMode.SELECT)
    @org.hibernate.annotations.Immutable
    private Set<Finding> highFindings;

    @ManyToMany
    @JoinTable(
    name = "package_medium_finding",
    joinColumns = {@JoinColumn(name = "package_id")},
    inverseJoinColumns = {@JoinColumn(name = "finding_id")}
    )
    @Fetch(FetchMode.SELECT)
    @org.hibernate.annotations.Immutable
    private Set<Finding> mediumFindings;

    @ManyToMany
    @JoinTable(
    name = "package_low_finding",
    joinColumns = {@JoinColumn(name = "package_id")},
    inverseJoinColumns = {@JoinColumn(name = "finding_id")}
    )
    @Fetch(FetchMode.SELECT)
    @org.hibernate.annotations.Immutable
    private Set<Finding> lowFindings;

    @ManyToMany(mappedBy = "packages")
    @Builder.Default
    private Set<DatasourceEvent> datasourceEvents = new HashSet<>();

    // @ManyToMany()
    // @JoinTable(
    //     name = "package_datasource",
    //     joinColumns = { @JoinColumn(name = "package_id") },
    //     inverseJoinColumns = { @JoinColumn(name = "datasource_id") }
    // )
    // @Builder.Default
    // @Fetch(FetchMode.JOIN)
    // private Set<Datasource> datasources = new HashSet<>();

    //

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*
     * PURL and associated fields should be set by setting purl 
     */

    @Setter(AccessLevel.NONE)
    @Column(nullable = false, unique = true)
    private String purl;

    public void setPurl(String purl) {
        try {
            // to this first to validate purl is valid 
            var purlObj = new PackageURL(purl);

            // if we're still here it's ok to set purl and constituent fields 
            this.purl = purl;
            this.type = purlObj.getType();
            this.namespace = purlObj.getNamespace();
            this.name = purlObj.getName(); 
            this.version = purlObj.getVersion(); // ok if null         
        } catch (MalformedPackageURLException e) {
            log.error("{} is not a valid PackageURL", purl);
        }
    }

    @Column(nullable = false)
    private String type;

    @Column()
    private String namespace;

    @Column(nullable = false)
    private String name;

    @Column()
    private String version; 

    /*
     * 
     */

    @Column()
    private int numberVersionsBehindHead;

    @Column()
    private int numberMajorVersionsBehindHead;

    @Column()
    private int numberMinorVersionsBehindHead;

    @Column()
    private int numberPatchVersionsBehindHead;

    @Column()
    private String mostRecentVersion;

    @Column()
    private ZonedDateTime mostRecentVersionPublishedAt;

    @Column()
    private ZonedDateTime thisVersionPublishedAt;

    @Column(nullable = false)  
    private ZonedDateTime updatedAt;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((findings == null) ? 0 : findings.hashCode());
        result = prime * result + ((purl == null) ? 0 : purl.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Package other = (Package) obj;
        if (findings == null) {
            if (other.findings != null)
                return false;
        } else if (!findings.equals(other.findings))
            return false;
        if (purl == null) {
            if (other.purl != null)
                return false;
        } else if (!purl.equals(other.purl))
            return false;
        return true;
    }
}