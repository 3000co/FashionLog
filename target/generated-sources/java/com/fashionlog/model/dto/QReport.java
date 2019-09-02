package com.fashionlog.model.dto;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReport is a Querydsl query type for Report
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QReport extends EntityPathBase<Report> {

    private static final long serialVersionUID = 1054790749L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReport report = new QReport("report");

    public final StringPath checkHistory = createString("checkHistory");

    public final DateTimePath<java.sql.Timestamp> checkTime = createDateTime("checkTime", java.sql.Timestamp.class);

    public final StringPath reason = createString("reason");

    public final QMember reportMemNo;

    public final NumberPath<Integer> reportNo = createNumber("reportNo", Integer.class);

    public final DateTimePath<java.sql.Timestamp> reportTime = createDateTime("reportTime", java.sql.Timestamp.class);

    public final QComment targetCommentNo;

    public final QMember targetMemNo;

    public final QPost targetPostNo;

    public final NumberPath<Integer> type = createNumber("type", Integer.class);

    public QReport(String variable) {
        this(Report.class, forVariable(variable), INITS);
    }

    public QReport(Path<? extends Report> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReport(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReport(PathMetadata metadata, PathInits inits) {
        this(Report.class, metadata, inits);
    }

    public QReport(Class<? extends Report> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.reportMemNo = inits.isInitialized("reportMemNo") ? new QMember(forProperty("reportMemNo"), inits.get("reportMemNo")) : null;
        this.targetCommentNo = inits.isInitialized("targetCommentNo") ? new QComment(forProperty("targetCommentNo"), inits.get("targetCommentNo")) : null;
        this.targetMemNo = inits.isInitialized("targetMemNo") ? new QMember(forProperty("targetMemNo"), inits.get("targetMemNo")) : null;
        this.targetPostNo = inits.isInitialized("targetPostNo") ? new QPost(forProperty("targetPostNo"), inits.get("targetPostNo")) : null;
    }

}

