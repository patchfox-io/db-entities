package io.patchfox.db_entities.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QEdit is a Querydsl query type for Edit
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QEdit extends EntityPathBase<Edit> {

    private static final long serialVersionUID = -1869457460L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QEdit edit = new QEdit("edit");

    public final StringPath after = createString("after");

    public final NumberPath<Integer> avoidsVulnerabilitiesRank = createNumber("avoidsVulnerabilitiesRank", Integer.class);

    public final StringPath before = createString("before");

    public final DateTimePath<java.time.ZonedDateTime> commitDateTime = createDateTime("commitDateTime", java.time.ZonedDateTime.class);

    public final NumberPath<Integer> criticalFindings = createNumber("criticalFindings", Integer.class);

    public final QDatasetMetrics datasetMetrics;

    public final QDatasource datasource;

    public final NumberPath<Integer> decreaseBacklogRank = createNumber("decreaseBacklogRank", Integer.class);

    public final NumberPath<Integer> decreaseVulnerabilityCountRank = createNumber("decreaseVulnerabilityCountRank", Integer.class);

    public final EnumPath<Edit.EditType> editType = createEnum("editType", Edit.EditType.class);

    public final DateTimePath<java.time.ZonedDateTime> eventDateTime = createDateTime("eventDateTime", java.time.ZonedDateTime.class);

    public final NumberPath<Integer> growPatchEfficacyIndex = createNumber("growPatchEfficacyIndex", Integer.class);

    public final NumberPath<Integer> highFindings = createNumber("highFindings", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> increaseImpactRank = createNumber("increaseImpactRank", Integer.class);

    public final BooleanPath isPfRecommendedEdit = createBoolean("isPfRecommendedEdit");

    public final BooleanPath isSameEdit = createBoolean("isSameEdit");

    public final BooleanPath isUserEdit = createBoolean("isUserEdit");

    public final NumberPath<Integer> lowFindings = createNumber("lowFindings", Integer.class);

    public final NumberPath<Integer> mediumFindings = createNumber("mediumFindings", Integer.class);

    public final NumberPath<Integer> reduceCveBacklogGrowthIndex = createNumber("reduceCveBacklogGrowthIndex", Integer.class);

    public final NumberPath<Integer> reduceCveBacklogIndex = createNumber("reduceCveBacklogIndex", Integer.class);

    public final NumberPath<Integer> reduceCveGrowthIndex = createNumber("reduceCveGrowthIndex", Integer.class);

    public final NumberPath<Integer> reduceCvesIndex = createNumber("reduceCvesIndex", Integer.class);

    public final NumberPath<Integer> reduceDownlevelPackagesGrowthIndex = createNumber("reduceDownlevelPackagesGrowthIndex", Integer.class);

    public final NumberPath<Integer> reduceDownlevelPackagesIndex = createNumber("reduceDownlevelPackagesIndex", Integer.class);

    public final NumberPath<Integer> reduceStalePackagesGrowthIndex = createNumber("reduceStalePackagesGrowthIndex", Integer.class);

    public final NumberPath<Integer> reduceStalePackagesIndex = createNumber("reduceStalePackagesIndex", Integer.class);

    public final NumberPath<Integer> removeRedundantPackagesIndex = createNumber("removeRedundantPackagesIndex", Integer.class);

    public final NumberPath<Integer> sameEditCount = createNumber("sameEditCount", Integer.class);

    public QEdit(String variable) {
        this(Edit.class, forVariable(variable), INITS);
    }

    public QEdit(Path<? extends Edit> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QEdit(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QEdit(PathMetadata metadata, PathInits inits) {
        this(Edit.class, metadata, inits);
    }

    public QEdit(Class<? extends Edit> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.datasetMetrics = inits.isInitialized("datasetMetrics") ? new QDatasetMetrics(forProperty("datasetMetrics"), inits.get("datasetMetrics")) : null;
        this.datasource = inits.isInitialized("datasource") ? new QDatasource(forProperty("datasource")) : null;
    }

}

