package com.fashionlog.model.dto;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = 911542723L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMember member = new QMember("member1");

    public final StringPath email = createString("email");

    public final ListPath<Follow, QFollow> followees = this.<Follow, QFollow>createList("followees", Follow.class, QFollow.class, PathInits.DIRECT2);

    public final ListPath<Follow, QFollow> followers = this.<Follow, QFollow>createList("followers", Follow.class, QFollow.class, PathInits.DIRECT2);

    public final StringPath id = createString("id");

    public final NumberPath<Integer> memberNo = createNumber("memberNo", Integer.class);

    public final StringPath nickname = createString("nickname");

    public final StringPath password = createString("password");

    public final StringPath phonenumber = createString("phonenumber");

    public final ListPath<Post, QPost> posts = this.<Post, QPost>createList("posts", Post.class, QPost.class, PathInits.DIRECT2);

    public final QFile profileImageNo;

    public final QStyle styleNo1;

    public final QStyle styleNo2;

    public final QStyle styleNo3;

    public QMember(String variable) {
        this(Member.class, forVariable(variable), INITS);
    }

    public QMember(Path<? extends Member> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMember(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMember(PathMetadata metadata, PathInits inits) {
        this(Member.class, metadata, inits);
    }

    public QMember(Class<? extends Member> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.profileImageNo = inits.isInitialized("profileImageNo") ? new QFile(forProperty("profileImageNo")) : null;
        this.styleNo1 = inits.isInitialized("styleNo1") ? new QStyle(forProperty("styleNo1")) : null;
        this.styleNo2 = inits.isInitialized("styleNo2") ? new QStyle(forProperty("styleNo2")) : null;
        this.styleNo3 = inits.isInitialized("styleNo3") ? new QStyle(forProperty("styleNo3")) : null;
    }

}

