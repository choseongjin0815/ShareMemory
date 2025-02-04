package com.chobocho.ShareMemory_back_end.domain.friend.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFriend is a Querydsl query type for Friend
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFriend extends EntityPathBase<Friend> {

    private static final long serialVersionUID = -2073789532L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFriend friend = new QFriend("friend");

    public final NumberPath<Long> friendId = createNumber("friendId", Long.class);

    public final EnumPath<FriendStatus> friendStatus = createEnum("friendStatus", FriendStatus.class);

    public final com.chobocho.ShareMemory_back_end.domain.user.domain.QUser fromUserId;

    public final DatePath<java.time.LocalDate> regDate = createDate("regDate", java.time.LocalDate.class);

    public final com.chobocho.ShareMemory_back_end.domain.user.domain.QUser toUserId;

    public QFriend(String variable) {
        this(Friend.class, forVariable(variable), INITS);
    }

    public QFriend(Path<? extends Friend> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFriend(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFriend(PathMetadata metadata, PathInits inits) {
        this(Friend.class, metadata, inits);
    }

    public QFriend(Class<? extends Friend> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.fromUserId = inits.isInitialized("fromUserId") ? new com.chobocho.ShareMemory_back_end.domain.user.domain.QUser(forProperty("fromUserId")) : null;
        this.toUserId = inits.isInitialized("toUserId") ? new com.chobocho.ShareMemory_back_end.domain.user.domain.QUser(forProperty("toUserId")) : null;
    }

}

