package io.patchfox.db_entities.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPackage is a Querydsl query type for Package
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPackage extends EntityPathBase<Package> {

    private static final long serialVersionUID = 914742084L;

    public static final QPackage package$ = new QPackage("package$");

    public final SetPath<Finding, QFinding> criticalFindings = this.<Finding, QFinding>createSet("criticalFindings", Finding.class, QFinding.class, PathInits.DIRECT2);

    public final SetPath<DatasourceEvent, QDatasourceEvent> datasourceEvents = this.<DatasourceEvent, QDatasourceEvent>createSet("datasourceEvents", DatasourceEvent.class, QDatasourceEvent.class, PathInits.DIRECT2);

    public final SetPath<Finding, QFinding> findings = this.<Finding, QFinding>createSet("findings", Finding.class, QFinding.class, PathInits.DIRECT2);

    public final SetPath<Finding, QFinding> highFindings = this.<Finding, QFinding>createSet("highFindings", Finding.class, QFinding.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final SetPath<Finding, QFinding> lowFindings = this.<Finding, QFinding>createSet("lowFindings", Finding.class, QFinding.class, PathInits.DIRECT2);

    public final SetPath<Finding, QFinding> mediumFindings = this.<Finding, QFinding>createSet("mediumFindings", Finding.class, QFinding.class, PathInits.DIRECT2);

    public final StringPath mostRecentVersion = createString("mostRecentVersion");

    public final DateTimePath<java.time.ZonedDateTime> mostRecentVersionPublishedAt = createDateTime("mostRecentVersionPublishedAt", java.time.ZonedDateTime.class);

    public final StringPath name = createString("name");

    public final StringPath namespace = createString("namespace");

    public final NumberPath<Integer> numberMajorVersionsBehindHead = createNumber("numberMajorVersionsBehindHead", Integer.class);

    public final NumberPath<Integer> numberMinorVersionsBehindHead = createNumber("numberMinorVersionsBehindHead", Integer.class);

    public final NumberPath<Integer> numberPatchVersionsBehindHead = createNumber("numberPatchVersionsBehindHead", Integer.class);

    public final NumberPath<Integer> numberVersionsBehindHead = createNumber("numberVersionsBehindHead", Integer.class);

    public final StringPath purl = createString("purl");

    public final DateTimePath<java.time.ZonedDateTime> thisVersionPublishedAt = createDateTime("thisVersionPublishedAt", java.time.ZonedDateTime.class);

    public final StringPath type = createString("type");

    public final DateTimePath<java.time.ZonedDateTime> updatedAt = createDateTime("updatedAt", java.time.ZonedDateTime.class);

    public final StringPath version = createString("version");

    public QPackage(String variable) {
        super(Package.class, forVariable(variable));
    }

    public QPackage(Path<? extends Package> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPackage(PathMetadata metadata) {
        super(Package.class, metadata);
    }

}

