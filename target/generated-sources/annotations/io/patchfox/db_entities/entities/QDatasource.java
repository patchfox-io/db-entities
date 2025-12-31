package io.patchfox.db_entities.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDatasource is a Querydsl query type for Datasource
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDatasource extends EntityPathBase<Datasource> {

    private static final long serialVersionUID = 1673298055L;

    public static final QDatasource datasource = new QDatasource("datasource");

    public final StringPath commitBranch = createString("commitBranch");

    public final SetPath<Dataset, QDataset> datasets = this.<Dataset, QDataset>createSet("datasets", Dataset.class, QDataset.class, PathInits.DIRECT2);

    public final StringPath domain = createString("domain");

    public final SetPath<Edit, QEdit> edits = this.<Edit, QEdit>createSet("edits", Edit.class, QEdit.class, PathInits.DIRECT2);

    public final DateTimePath<java.time.ZonedDateTime> firstEventReceivedAt = createDateTime("firstEventReceivedAt", java.time.ZonedDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.time.ZonedDateTime> lastEventReceivedAt = createDateTime("lastEventReceivedAt", java.time.ZonedDateTime.class);

    public final StringPath lastEventReceivedStatus = createString("lastEventReceivedStatus");

    public final ComparablePath<java.util.UUID> latestJobId = createComparable("latestJobId", java.util.UUID.class);

    public final ComparablePath<java.util.UUID> latestTxid = createComparable("latestTxid", java.util.UUID.class);

    public final StringPath name = createString("name");

    public final NumberPath<Double> numberEventProcessingErrors = createNumber("numberEventProcessingErrors", Double.class);

    public final NumberPath<Double> numberEventsReceived = createNumber("numberEventsReceived", Double.class);

    public final ListPath<Long, NumberPath<Long>> packageIndexes = this.<Long, NumberPath<Long>>createList("packageIndexes", Long.class, NumberPath.class, PathInits.DIRECT2);

    public final StringPath purl = createString("purl");

    public final EnumPath<Datasource.Status> status = createEnum("status", Datasource.Status.class);

    public final StringPath type = createString("type");

    public QDatasource(String variable) {
        super(Datasource.class, forVariable(variable));
    }

    public QDatasource(Path<? extends Datasource> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDatasource(PathMetadata metadata) {
        super(Datasource.class, metadata);
    }

}

