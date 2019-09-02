package com.fashionlog.model.dto;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QNotification is a Querydsl query type for Notification
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QNotification extends EntityPathBase<Notification> {

    private static final long serialVersionUID = -1798224556L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QNotification notification = new QNotification("notification");

    public final DateTimePath<java.sql.Timestamp> checkTime = createDateTime("checkTime", java.sql.Timestamp.class);

    public final QComment commentNo;

    public final QFollow followNo;

    public final QLikes likesNo;

    public final NumberPath<Integer> notiNo = createNumber("notiNo", Integer.class);

    public final QMember recieverMemNo;

    public final QMember senderMemNo;

    public final DateTimePath<java.sql.Timestamp> sendTime = createDateTime("sendTime", java.sql.Timestamp.class);

    public final NumberPath<Integer> type = createNumber("type", Integer.class);

    public QNotification(String variable) {
        this(Notification.class, forVariable(variable), INITS);
    }

    public QNotification(Path<? extends Notification> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QNotification(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QNotification(PathMetadata metadata, PathInits inits) {
        this(Notification.class, metadata, inits);
    }

    public QNotification(Class<? extends Notification> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.commentNo = inits.isInitialized("commentNo") ? new QComment(forProperty("commentNo"), inits.get("commentNo")) : null;
        this.followNo = inits.isInitialized("followNo") ? new QFollow(forProperty("followNo"), inits.get("followNo")) : null;
        this.likesNo = inits.isInitialized("likesNo") ? new QLikes(forProperty("likesNo"), inits.get("likesNo")) : null;
        this.recieverMemNo = inits.isInitialized("recieverMemNo") ? new QMember(forProperty("recieverMemNo"), inits.get("recieverMemNo")) : null;
        this.senderMemNo = inits.isInitialized("senderMemNo") ? new QMember(forProperty("senderMemNo"), inits.get("senderMemNo")) : null;
    }

}

