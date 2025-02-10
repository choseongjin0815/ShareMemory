package com.chobocho.ShareMemory_back_end.domain.user.dto;

import com.chobocho.ShareMemory_back_end.domain.user.domain.User;
import com.chobocho.ShareMemory_back_end.domain.user.domain.UserStatus;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@ToString
@Builder

public class UserDTO extends org.springframework.security.core.userdetails.User {
    private String userId;

    private String pwd;

    private String nickname;

    private LocalDate regDate;

    private UserStatus userStatus;

    public UserDTO(String userId, String pwd, String nickname, LocalDate regDate, UserStatus userStatus) {
        super(
                userId,
                pwd
                );
    }

    public User toEntity() {
        return User.builder()
                .userId(userId)
                .pwd(pwd)
                .nickname(nickname)
                .regDate(LocalDate.now())
                .userStatus(userStatus)
                .build();
    }



    public Map<String, Object> getClaims() {

        Map<String, Object> dataMap = new HashMap<>();

        dataMap.put("userId", userId);
        dataMap.put("pwd",pwd);
        dataMap.put("nickname", nickname);
        dataMap.put("regData", regDate);
        dataMap.put("userStatus", userStatus);

        return dataMap;
    }
}
