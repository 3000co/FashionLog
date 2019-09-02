package com.fashionlog.model.dto;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QItem is a Querydsl query type for Item
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QItem extends EntityPathBase<Item> {

    private static final long serialVersionUID = 1618718844L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QItem item = new QItem("item");

    public final QBrand brandNo;

    public final QCategory categoryNo;

    public final StringPath color = createString("color");

    public final NumberPath<Integer> itemNo = createNumber("itemNo", Integer.class);

    public final StringPath name = createString("name");

    public final QPost postNo;

    public final StringPath store = createString("store");

    public final NumberPath<Integer> tagNo = createNumber("tagNo", Integer.class);

    public final NumberPath<Float> xCoordinate = createNumber("xCoordinate", Float.class);

    public final NumberPath<Float> yCoordinate = createNumber("yCoordinate", Float.class);

    public QItem(String variable) {
        this(Item.class, forVariable(variable), INITS);
    }

    public QItem(Path<? extends Item> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QItem(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QItem(PathMetadata metadata, PathInits inits) {
        this(Item.class, metadata, inits);
    }

    public QItem(Class<? extends Item> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.brandNo = inits.isInitialized("brandNo") ? new QBrand(forProperty("brandNo"), inits.get("brandNo")) : null;
        this.categoryNo = inits.isInitialized("categoryNo") ? new QCategory(forProperty("categoryNo")) : null;
        this.postNo = inits.isInitialized("postNo") ? new QPost(forProperty("postNo"), inits.get("postNo")) : null;
    }

}

