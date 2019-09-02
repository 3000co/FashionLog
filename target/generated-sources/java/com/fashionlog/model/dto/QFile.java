package com.fashionlog.model.dto;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QFile is a Querydsl query type for File
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QFile extends EntityPathBase<File> {

    private static final long serialVersionUID = 1618619109L;

    public static final QFile file = new QFile("file");

    public final DateTimePath<java.sql.Timestamp> deleteTime = createDateTime("deleteTime", java.sql.Timestamp.class);

    public final NumberPath<Integer> fileNo = createNumber("fileNo", Integer.class);

    public final StringPath name = createString("name");

    public final StringPath path = createString("path");

    public final NumberPath<Integer> size = createNumber("size", Integer.class);

    public final StringPath state = createString("state");

    public final StringPath type = createString("type");

    public final DateTimePath<java.sql.Timestamp> uploadTime = createDateTime("uploadTime", java.sql.Timestamp.class);

    public QFile(String variable) {
        super(File.class, forVariable(variable));
    }

    public QFile(Path<? extends File> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFile(PathMetadata metadata) {
        super(File.class, metadata);
    }

}

