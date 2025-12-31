package io.patchfox.db_entities.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QDatasourceMetricsCurrent is a Querydsl query type for DatasourceMetricsCurrent
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDatasourceMetricsCurrent extends EntityPathBase<DatasourceMetricsCurrent> {

    private static final long serialVersionUID = -277801059L;

    public static final QDatasourceMetricsCurrent datasourceMetricsCurrent = new QDatasourceMetricsCurrent("datasourceMetricsCurrent");

    public final DateTimePath<java.time.ZonedDateTime> commitDateTime = createDateTime("commitDateTime", java.time.ZonedDateTime.class);

    public final NumberPath<Long> criticalFindings = createNumber("criticalFindings", Long.class);

    public final NumberPath<Long> datasourceEventCount = createNumber("datasourceEventCount", Long.class);

    public final NumberPath<Long> differentPatches = createNumber("differentPatches", Long.class);

    public final NumberPath<Long> downlevelPackages = createNumber("downlevelPackages", Long.class);

    public final NumberPath<Long> downlevelPackagesMajor = createNumber("downlevelPackagesMajor", Long.class);

    public final NumberPath<Long> downlevelPackagesMinor = createNumber("downlevelPackagesMinor", Long.class);

    public final NumberPath<Long> downlevelPackagesPatch = createNumber("downlevelPackagesPatch", Long.class);

    public final DateTimePath<java.time.ZonedDateTime> eventDateTime = createDateTime("eventDateTime", java.time.ZonedDateTime.class);

    public final NumberPath<Long> highFindings = createNumber("highFindings", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ComparablePath<java.util.UUID> jobId = createComparable("jobId", java.util.UUID.class);

    public final NumberPath<Long> lowFindings = createNumber("lowFindings", Long.class);

    public final NumberPath<Long> mediumFindings = createNumber("mediumFindings", Long.class);

    public final NumberPath<Long> packages = createNumber("packages", Long.class);

    public final NumberPath<Long> packagesWithCriticalFindings = createNumber("packagesWithCriticalFindings", Long.class);

    public final NumberPath<Long> packagesWithFindings = createNumber("packagesWithFindings", Long.class);

    public final NumberPath<Long> packagesWithHighFindings = createNumber("packagesWithHighFindings", Long.class);

    public final NumberPath<Long> packagesWithLowFindings = createNumber("packagesWithLowFindings", Long.class);

    public final NumberPath<Long> packagesWithMediumFindings = createNumber("packagesWithMediumFindings", Long.class);

    public final NumberPath<Long> patches = createNumber("patches", Long.class);

    public final NumberPath<Long> patchFoxPatches = createNumber("patchFoxPatches", Long.class);

    public final StringPath purl = createString("purl");

    public final NumberPath<Long> samePatches = createNumber("samePatches", Long.class);

    public final NumberPath<Long> stalePackages = createNumber("stalePackages", Long.class);

    public final NumberPath<Long> totalFindings = createNumber("totalFindings", Long.class);

    public final ComparablePath<java.util.UUID> txid = createComparable("txid", java.util.UUID.class);

    public QDatasourceMetricsCurrent(String variable) {
        super(DatasourceMetricsCurrent.class, forVariable(variable));
    }

    public QDatasourceMetricsCurrent(Path<? extends DatasourceMetricsCurrent> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDatasourceMetricsCurrent(PathMetadata metadata) {
        super(DatasourceMetricsCurrent.class, metadata);
    }

}

