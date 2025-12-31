package io.patchfox.db_entities.entities;

import java.time.ZonedDateTime;
import java.util.Set;
import java.util.HashSet;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FindingData {
    
    @OneToOne
    @JoinColumn(name = "finding_id", referencedColumnName = "id")
    private Finding finding;

    //

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String identifier;

    @Column(nullable = false)
    private String severity; 

    @Column(nullable = false, length = 8192)
    private String description;

    @Column(nullable = false)
    private Set<String> cpes;

    @Column(nullable = false)  
    private ZonedDateTime reportedAt;

    @Column()  
    private ZonedDateTime publishedAt;

    @Column()
    @Builder.Default
    private Set<String> patchedIn = new HashSet<>();

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((identifier == null) ? 0 : identifier.hashCode());
        result = prime * result + ((severity == null) ? 0 : severity.hashCode());
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
        FindingData other = (FindingData) obj;
        if (identifier == null) {
            if (other.identifier != null)
                return false;
        } else if (!identifier.equals(other.identifier))
            return false;
        if (severity == null) {
            if (other.severity != null)
                return false;
        } else if (!severity.equals(other.severity))
            return false;
        return true;
    }


    
}
