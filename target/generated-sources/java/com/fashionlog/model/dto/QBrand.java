package com.fashionlog.model.dto;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBrand is a Querydsl query type for Brand
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBrand extends EntityPathBase<Brand> {

    private static final long serialVersionUID = -1365851330L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBrand brand = new QBrand("brand");

    public final QFile brandImageNo;

    public final NumberPath<Integer> brandNo = createNumber("brandNo", Integer.class);

    public final StringPath name = createString("name");

    public QBrand(String variable) {
        this(Brand.class, forVariable(variable), INITS);
    }

    public QBrand(Path<? extends Brand> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBrand(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBrand(PathMetadata metadata, PathInits inits) {
        this(Brand.class, metadata, inits);
    }

    public QBrand(Class<? extends Brand> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.brandImageNo = inits.isInitialized("brandImageNo") ? new QFile(forProperty("brandImageNo")) : null;
    }

}

