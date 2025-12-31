package io.patchfox.db_entities.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFinding is a Querydsl query type for Finding
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFinding extends EntityPathBase<Finding> {

    private static final long serialVersionUID = 868631175L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFinding finding = new QFinding("finding");

    public final QFindingData data;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath identifier = createString("identifier");

    public final SetPath<Package, QPackage> packages = this.<Package, QPackage>createSet("packages", Package.class, QPackage.class, PathInits.DIRECT2);

    public final SetPath<FindingReporter, QFindingReporter> reporters = this.<FindingReporter, QFindingReporter>createSet("reporters", FindingReporter.class, QFindingReporter.class, PathInits.DIRECT2);

    public QFinding(String variable) {
        this(Finding.class, forVariable(variable), INITS);
    }

    public QFinding(Path<? extends Finding> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFinding(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFinding(PathMetadata metadata, PathInits inits) {
        this(Finding.class, metadata, inits);
    }

    public QFinding(Class<? extends Finding> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.data = inits.isInitialized("data") ? new QFindingData(forProperty("data"), inits.get("data")) : null;
    }

}

