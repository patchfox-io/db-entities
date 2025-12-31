package io.patchfox.db_entities.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDataset is a Querydsl query type for Dataset
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDataset extends EntityPathBase<Dataset> {

    private static final long serialVersionUID = -1129948298L;

    public static final QDataset dataset = new QDataset("dataset");

    public final SetPath<Datasource, QDatasource> datasources = this.<Datasource, QDatasource>createSet("datasources", Datasource.class, QDatasource.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ComparablePath<java.util.UUID> latestJobId = createComparable("latestJobId", java.util.UUID.class);

    public final ComparablePath<java.util.UUID> latestTxid = createComparable("latestTxid", java.util.UUID.class);

    public final StringPath name = createString("name");

    public final EnumPath<Dataset.Status> status = createEnum("status", Dataset.Status.class);

    public final DateTimePath<java.time.ZonedDateTime> updatedAt = createDateTime("updatedAt", java.time.ZonedDateTime.class);

    public QDataset(String variable) {
        super(Dataset.class, forVariable(variable));
    }

    public QDataset(Path<? extends Dataset> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDataset(PathMetadata metadata) {
        super(Dataset.class, metadata);
    }

}

