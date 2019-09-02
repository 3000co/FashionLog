package com.fashionlog.model.dto;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPost is a Querydsl query type for Post
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPost extends EntityPathBase<Post> {

    private static final long serialVersionUID = 1618923017L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPost post = new QPost("post");

    public final ListPath<Comment, QComment> commentList = this.<Comment, QComment>createList("commentList", Comment.class, QComment.class, PathInits.DIRECT2);

    public final StringPath contents = createString("contents");

    public final ListPath<Item, QItem> itemList = this.<Item, QItem>createList("itemList", Item.class, QItem.class, PathInits.DIRECT2);

    public final QMember memberNo;

    public final QFile postImageNo;

    public final NumberPath<Integer> postNo = createNumber("postNo", Integer.class);

    public final QStyle styleNo1;

    public final QStyle styleNo2;

    public final QStyle styleNo3;

    public final DateTimePath<java.sql.Timestamp> uploadTime = createDateTime("uploadTime", java.sql.Timestamp.class);

    public QPost(String variable) {
        this(Post.class, forVariable(variable), INITS);
    }

    public QPost(Path<? extends Post> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPost(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPost(PathMetadata metadata, PathInits inits) {
        this(Post.class, metadata, inits);
    }

    public QPost(Class<? extends Post> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.memberNo = inits.isInitialized("memberNo") ? new QMember(forProperty("memberNo"), inits.get("memberNo")) : null;
        this.postImageNo = inits.isInitialized("postImageNo") ? new QFile(forProperty("postImageNo")) : null;
        this.styleNo1 = inits.isInitialized("styleNo1") ? new QStyle(forProperty("styleNo1")) : null;
        this.styleNo2 = inits.isInitialized("styleNo2") ? new QStyle(forProperty("styleNo2")) : null;
        this.styleNo3 = inits.isInitialized("styleNo3") ? new QStyle(forProperty("styleNo3")) : null;
    }

}

