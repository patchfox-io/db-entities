package io.patchfox.db_entities.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFindingReporter is a Querydsl query type for FindingReporter
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFindingReporter extends EntityPathBase<FindingReporter> {

    private static final long serialVersionUID = -857664376L;

    public static final QFindingReporter findingReporter = new QFindingReporter("findingReporter");

    public final SetPath<Finding, QFinding> findings = this.<Finding, QFinding>createSet("findings", Finding.class, QFinding.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public QFindingReporter(String variable) {
        super(FindingReporter.class, forVariable(variable));
    }

    public QFindingReporter(Path<? extends FindingReporter> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFindingReporter(PathMetadata metadata) {
        super(FindingReporter.class, metadata);
    }

}

