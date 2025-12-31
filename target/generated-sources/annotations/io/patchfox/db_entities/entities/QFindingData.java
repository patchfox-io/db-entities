package io.patchfox.db_entities.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFindingData is a Querydsl query type for FindingData
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFindingData extends EntityPathBase<FindingData> {

    private static final long serialVersionUID = -1973155119L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFindingData findingData = new QFindingData("findingData");

    public final SetPath<String, StringPath> cpes = this.<String, StringPath>createSet("cpes", String.class, StringPath.class, PathInits.DIRECT2);

    public final StringPath description = createString("description");

    public final QFinding finding;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath identifier = createString("identifier");

    public final SetPath<String, StringPath> patchedIn = this.<String, StringPath>createSet("patchedIn", String.class, StringPath.class, PathInits.DIRECT2);

    public final DateTimePath<java.time.ZonedDateTime> publishedAt = createDateTime("publishedAt", java.time.ZonedDateTime.class);

    public final DateTimePath<java.time.ZonedDateTime> reportedAt = createDateTime("reportedAt", java.time.ZonedDateTime.class);

    public final StringPath severity = createString("severity");

    public QFindingData(String variable) {
        this(FindingData.class, forVariable(variable), INITS);
    }

    public QFindingData(Path<? extends FindingData> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFindingData(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFindingData(PathMetadata metadata, PathInits inits) {
        this(FindingData.class, metadata, inits);
    }

    public QFindingData(Class<? extends FindingData> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.finding = inits.isInitialized("finding") ? new QFinding(forProperty("finding"), inits.get("finding")) : null;
    }

}

