package com.chobocho.ShareMemory_back_end.domain.user.dto;

import com.chobocho.ShareMemory_back_end.domain.user.domain.User;
import com.chobocho.ShareMemory_back_end.domain.user.domain.UserStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Getter
@Setter
@ToString

public class UserDTO extends org.springframework.security.core.userdetails.User {
    private String userId;

    private String pwd;

    private String nickname;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate regDate;

    private UserStatus userStatus;

    //초기 버전은 권한 없으므로 빈 리스트로 진행할 예정
    private Collection<GrantedAuthority> authorities;

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return authorities;
    }


    public UserDTO(String userId, String pwd, String nickname, LocalDate regDate, UserStatus userStatus, Collection<GrantedAuthority> authorities) {
        super(
                //만약 authorites가 없으므로 빈리스트로 저장
                userId,
                pwd,
                (authorities != null) ? authorities : Collections.emptyList());
        this.userId = userId;
        this.pwd = pwd;
        this.userStatus = userStatus;
        this.regDate = regDate;
        this.nickname = nickname;
        this.authorities = (authorities != null) ? authorities : Collections.emptyList();

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
        dataMap.put("regDate", regDate.format(DateTimeFormatter.ISO_LOCAL_DATE));
        dataMap.put("userStatus", userStatus);

        return dataMap;
    }
}
