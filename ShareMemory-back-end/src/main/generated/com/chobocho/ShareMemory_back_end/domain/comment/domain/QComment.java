package com.chobocho.ShareMemory_back_end.domain.comment.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QComment is a Querydsl query type for Comment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QComment extends EntityPathBase<Comment> {

    private static final long serialVersionUID = 125035344L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QComment comment = new QComment("comment");

    public final com.chobocho.ShareMemory_back_end.util.base.QBaseEntity _super = new com.chobocho.ShareMemory_back_end.util.base.QBaseEntity(this);

    public final NumberPath<Long> cno = createNumber("cno", Long.class);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> created_at = _super.created_at;

    public final com.chobocho.ShareMemory_back_end.domain.diary.domain.QDiary diary;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updated_at = _super.updated_at;

    public final com.chobocho.ShareMemory_back_end.domain.user.domain.QUser userId;

    public QComment(String variable) {
        this(Comment.class, forVariable(variable), INITS);
    }

    public QComment(Path<? extends Comment> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QComment(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QComment(PathMetadata metadata, PathInits inits) {
        this(Comment.class, metadata, inits);
    }

    public QComment(Class<? extends Comment> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.diary = inits.isInitialized("diary") ? new com.chobocho.ShareMemory_back_end.domain.diary.domain.QDiary(forProperty("diary"), inits.get("diary")) : null;
        this.userId = inits.isInitialized("userId") ? new com.chobocho.ShareMemory_back_end.domain.user.domain.QUser(forProperty("userId")) : null;
    }

}

