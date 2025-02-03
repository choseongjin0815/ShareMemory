package com.chobocho.ShareMemory_back_end.domain.friend.dto;

import com.chobocho.ShareMemory_back_end.domain.friend.domain.FriendStatus;
import com.chobocho.ShareMemory_back_end.domain.user.domain.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class FriendDTO {

    private Long friendId;


    private String fromUserId;


    private String toUserId;


    private List<String> friendStatusList = new ArrayList<>();
}
