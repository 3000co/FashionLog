package com.fashionlog.model.dto;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFollow is a Querydsl query type for Follow
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QFollow extends EntityPathBase<Follow> {

    private static final long serialVersionUID = 720354010L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFollow follow = new QFollow("follow");

    public final QMember followeeMemNo;

    public final QMember followerMemNo;

    public final NumberPath<Integer> followNo = createNumber("followNo", Integer.class);

    public final DateTimePath<java.sql.Timestamp> followTime = createDateTime("followTime", java.sql.Timestamp.class);

    public QFollow(String variable) {
        this(Follow.class, forVariable(variable), INITS);
    }

    public QFollow(Path<? extends Follow> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFollow(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFollow(PathMetadata metadata, PathInits inits) {
        this(Follow.class, metadata, inits);
    }

    public QFollow(Class<? extends Follow> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.followeeMemNo = inits.isInitialized("followeeMemNo") ? new QMember(forProperty("followeeMemNo"), inits.get("followeeMemNo")) : null;
        this.followerMemNo = inits.isInitialized("followerMemNo") ? new QMember(forProperty("followerMemNo"), inits.get("followerMemNo")) : null;
    }

}

