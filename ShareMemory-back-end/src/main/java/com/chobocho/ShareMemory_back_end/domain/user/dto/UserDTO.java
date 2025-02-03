package com.chobocho.ShareMemory_back_end.domain.user.dto;

import com.chobocho.ShareMemory_back_end.domain.user.domain.User;
import com.chobocho.ShareMemory_back_end.domain.user.domain.UserStatus;
import jakarta.persistence.Id;
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
public class UserDTO {
    private String userId;

    private String pwd;

    private String nickname;

    private LocalDate regDate;

    private UserStatus userStatus;

    public User toEntity() {
        return User.builder()
                .userId(userId)
                .pwd(pwd)
                .nickname(nickname)
                .regDate(LocalDate.now())
                .userStatus(userStatus)
                .build();
    }
}
