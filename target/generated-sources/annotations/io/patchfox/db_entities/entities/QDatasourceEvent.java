package io.patchfox.db_entities.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDatasourceEvent is a Querydsl query type for DatasourceEvent
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDatasourceEvent extends EntityPathBase<DatasourceEvent> {

    private static final long serialVersionUID = -394969517L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDatasourceEvent datasourceEvent = new QDatasourceEvent("datasourceEvent");

    public final BooleanPath analyzed = createBoolean("analyzed");

    public final StringPath commitBranch = createString("commitBranch");

    public final DateTimePath<java.time.ZonedDateTime> commitDateTime = createDateTime("commitDateTime", java.time.ZonedDateTime.class);

    public final StringPath commitHash = createString("commitHash");

    public final QDatasource datasource;

    public final DateTimePath<java.time.ZonedDateTime> eventDateTime = createDateTime("eventDateTime", java.time.ZonedDateTime.class);

    public final BooleanPath forecasted = createBoolean("forecasted");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ComparablePath<java.util.UUID> jobId = createComparable("jobId", java.util.UUID.class);

    public final BooleanPath ossEnriched = createBoolean("ossEnriched");

    public final BooleanPath packageIndexEnriched = createBoolean("packageIndexEnriched");

    public final SetPath<Package, QPackage> packages = this.<Package, QPackage>createSet("packages", Package.class, QPackage.class, PathInits.DIRECT2);

    public final ArrayPath<byte[], Byte> payload = createArray("payload", byte[].class);

    public final StringPath processingError = createString("processingError");

    public final StringPath purl = createString("purl");

    public final BooleanPath recommended = createBoolean("recommended");

    public final EnumPath<DatasourceEvent.Status> status = createEnum("status", DatasourceEvent.Status.class);

    public final ComparablePath<java.util.UUID> txid = createComparable("txid", java.util.UUID.class);

    public QDatasourceEvent(String variable) {
        this(DatasourceEvent.class, forVariable(variable), INITS);
    }

    public QDatasourceEvent(Path<? extends DatasourceEvent> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDatasourceEvent(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDatasourceEvent(PathMetadata metadata, PathInits inits) {
        this(DatasourceEvent.class, metadata, inits);
    }

    public QDatasourceEvent(Class<? extends DatasourceEvent> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.datasource = inits.isInitialized("datasource") ? new QDatasource(forProperty("datasource")) : null;
    }

}

