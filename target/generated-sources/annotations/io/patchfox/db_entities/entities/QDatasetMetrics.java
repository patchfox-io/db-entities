package io.patchfox.db_entities.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDatasetMetrics is a Querydsl query type for DatasetMetrics
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDatasetMetrics extends EntityPathBase<DatasetMetrics> {

    private static final long serialVersionUID = 1703496813L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDatasetMetrics datasetMetrics = new QDatasetMetrics("datasetMetrics");

    public final DateTimePath<java.time.ZonedDateTime> commitDateTime = createDateTime("commitDateTime", java.time.ZonedDateTime.class);

    public final NumberPath<Long> criticalFindings = createNumber("criticalFindings", Long.class);

    public final NumberPath<Long> criticalFindingsAvoidedByPatchingPastYear = createNumber("criticalFindingsAvoidedByPatchingPastYear", Long.class);

    public final NumberPath<Double> criticalFindingsInBacklogBetweenSixtyAndNinetyDays = createNumber("criticalFindingsInBacklogBetweenSixtyAndNinetyDays", Double.class);

    public final NumberPath<Double> criticalFindingsInBacklogBetweenThirtyAndSixtyDays = createNumber("criticalFindingsInBacklogBetweenThirtyAndSixtyDays", Double.class);

    public final NumberPath<Double> criticalFindingsInBacklogOverNinetyDays = createNumber("criticalFindingsInBacklogOverNinetyDays", Double.class);

    public final QDataset dataset;

    public final NumberPath<Long> datasourceCount = createNumber("datasourceCount", Long.class);

    public final NumberPath<Long> datasourceEventCount = createNumber("datasourceEventCount", Long.class);

    public final NumberPath<Long> differentPatches = createNumber("differentPatches", Long.class);

    public final NumberPath<Long> downlevelPackages = createNumber("downlevelPackages", Long.class);

    public final NumberPath<Long> downlevelPackagesMajor = createNumber("downlevelPackagesMajor", Long.class);

    public final NumberPath<Long> downlevelPackagesMinor = createNumber("downlevelPackagesMinor", Long.class);

    public final NumberPath<Long> downlevelPackagesPatch = createNumber("downlevelPackagesPatch", Long.class);

    public final SetPath<Edit, QEdit> edits = this.<Edit, QEdit>createSet("edits", Edit.class, QEdit.class, PathInits.DIRECT2);

    public final DateTimePath<java.time.ZonedDateTime> eventDateTime = createDateTime("eventDateTime", java.time.ZonedDateTime.class);

    public final NumberPath<Long> findingsAvoidedByPatchingPastYear = createNumber("findingsAvoidedByPatchingPastYear", Long.class);

    public final NumberPath<Double> findingsInBacklogBetweenSixtyAndNinetyDays = createNumber("findingsInBacklogBetweenSixtyAndNinetyDays", Double.class);

    public final NumberPath<Double> findingsInBacklogBetweenThirtyAndSixtyDays = createNumber("findingsInBacklogBetweenThirtyAndSixtyDays", Double.class);

    public final NumberPath<Double> findingsInBacklogOverNinetyDays = createNumber("findingsInBacklogOverNinetyDays", Double.class);

    public final DateTimePath<java.time.ZonedDateTime> forecastMaturityDate = createDateTime("forecastMaturityDate", java.time.ZonedDateTime.class);

    public final NumberPath<Long> highFindings = createNumber("highFindings", Long.class);

    public final NumberPath<Long> highFindingsAvoidedByPatchingPastYear = createNumber("highFindingsAvoidedByPatchingPastYear", Long.class);

    public final NumberPath<Double> highFindingsInBacklogBetweenSixtyAndNinetyDays = createNumber("highFindingsInBacklogBetweenSixtyAndNinetyDays", Double.class);

    public final NumberPath<Double> highFindingsInBacklogBetweenThirtyAndSixtyDays = createNumber("highFindingsInBacklogBetweenThirtyAndSixtyDays", Double.class);

    public final NumberPath<Double> highFindingsInBacklogOverNinetyDays = createNumber("highFindingsInBacklogOverNinetyDays", Double.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isCurrent = createBoolean("isCurrent");

    public final BooleanPath isForecastRecommendationsTaken = createBoolean("isForecastRecommendationsTaken");

    public final BooleanPath isForecastSameCourse = createBoolean("isForecastSameCourse");

    public final ComparablePath<java.util.UUID> jobId = createComparable("jobId", java.util.UUID.class);

    public final NumberPath<Long> lowFindings = createNumber("lowFindings", Long.class);

    public final NumberPath<Long> lowFindingsAvoidedByPatchingPastYear = createNumber("lowFindingsAvoidedByPatchingPastYear", Long.class);

    public final NumberPath<Double> lowFindingsInBacklogBetweenSixtyAndNinetyDays = createNumber("lowFindingsInBacklogBetweenSixtyAndNinetyDays", Double.class);

    public final NumberPath<Double> lowFindingsInBacklogBetweenThirtyAndSixtyDays = createNumber("lowFindingsInBacklogBetweenThirtyAndSixtyDays", Double.class);

    public final NumberPath<Double> lowFindingsInBacklogOverNinetyDays = createNumber("lowFindingsInBacklogOverNinetyDays", Double.class);

    public final NumberPath<Long> mediumFindings = createNumber("mediumFindings", Long.class);

    public final NumberPath<Long> mediumFindingsAvoidedByPatchingPastYear = createNumber("mediumFindingsAvoidedByPatchingPastYear", Long.class);

    public final NumberPath<Double> mediumFindingsInBacklogBetweenSixtyAndNinetyDays = createNumber("mediumFindingsInBacklogBetweenSixtyAndNinetyDays", Double.class);

    public final NumberPath<Double> mediumFindingsInBacklogBetweenThirtyAndSixtyDays = createNumber("mediumFindingsInBacklogBetweenThirtyAndSixtyDays", Double.class);

    public final NumberPath<Double> mediumFindingsInBacklogOverNinetyDays = createNumber("mediumFindingsInBacklogOverNinetyDays", Double.class);

    public final SetPath<String, StringPath> packageFamilies = this.<String, StringPath>createSet("packageFamilies", String.class, StringPath.class, PathInits.DIRECT2);

    public final ListPath<Long, NumberPath<Long>> packageIndexes = this.<Long, NumberPath<Long>>createList("packageIndexes", Long.class, NumberPath.class, PathInits.DIRECT2);

    public final NumberPath<Long> packages = createNumber("packages", Long.class);

    public final NumberPath<Long> packagesWithCriticalFindings = createNumber("packagesWithCriticalFindings", Long.class);

    public final NumberPath<Long> packagesWithFindings = createNumber("packagesWithFindings", Long.class);

    public final NumberPath<Long> packagesWithHighFindings = createNumber("packagesWithHighFindings", Long.class);

    public final NumberPath<Long> packagesWithLowFindings = createNumber("packagesWithLowFindings", Long.class);

    public final NumberPath<Long> packagesWithMediumFindings = createNumber("packagesWithMediumFindings", Long.class);

    public final NumberPath<Double> patchEfficacyScore = createNumber("patchEfficacyScore", Double.class);

    public final NumberPath<Double> patchEffort = createNumber("patchEffort", Double.class);

    public final NumberPath<Long> patches = createNumber("patches", Long.class);

    public final NumberPath<Long> patchFoxPatches = createNumber("patchFoxPatches", Long.class);

    public final NumberPath<Double> patchImpact = createNumber("patchImpact", Double.class);

    public final StringPath recommendationHeadline = createString("recommendationHeadline");

    public final EnumPath<DatasetMetrics.RecommendationType> recommendationType = createEnum("recommendationType", DatasetMetrics.RecommendationType.class);

    public final NumberPath<Double> rpsScore = createNumber("rpsScore", Double.class);

    public final NumberPath<Long> samePatches = createNumber("samePatches", Long.class);

    public final NumberPath<Long> stalePackages = createNumber("stalePackages", Long.class);

    public final NumberPath<Long> stalePackagesOneYear = createNumber("stalePackagesOneYear", Long.class);

    public final NumberPath<Long> stalePackagesOneYearSixMonths = createNumber("stalePackagesOneYearSixMonths", Long.class);

    public final NumberPath<Long> stalePackagesSixMonths = createNumber("stalePackagesSixMonths", Long.class);

    public final NumberPath<Long> stalePackagesTwoYears = createNumber("stalePackagesTwoYears", Long.class);

    public final NumberPath<Long> totalFindings = createNumber("totalFindings", Long.class);

    public final ComparablePath<java.util.UUID> txid = createComparable("txid", java.util.UUID.class);

    public QDatasetMetrics(String variable) {
        this(DatasetMetrics.class, forVariable(variable), INITS);
    }

    public QDatasetMetrics(Path<? extends DatasetMetrics> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDatasetMetrics(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDatasetMetrics(PathMetadata metadata, PathInits inits) {
        this(DatasetMetrics.class, metadata, inits);
    }

    public QDatasetMetrics(Class<? extends DatasetMetrics> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.dataset = inits.isInitialized("dataset") ? new QDataset(forProperty("dataset")) : null;
    }

}

