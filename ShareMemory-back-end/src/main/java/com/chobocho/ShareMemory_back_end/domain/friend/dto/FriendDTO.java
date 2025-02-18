package com.chobocho.ShareMemory_back_end.domain.friend.dto;

import com.chobocho.ShareMemory_back_end.domain.friend.domain.Friend;
import com.chobocho.ShareMemory_back_end.domain.friend.domain.FriendStatus;
import com.chobocho.ShareMemory_back_end.domain.user.domain.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@Builder
public class FriendDTO {

    private Long friendId;


    private String fromUserId;


    private String toUserId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate regDate;


    private FriendStatus friendStatus;

    public Friend dtoToEntity(User sender, User receiver) {
        return Friend.builder()
                .fromUserId(sender)
                .toUserId(receiver)
                .friendStatus(this.friendStatus)
                .regDate(this.regDate)
                .build();
    }
}
