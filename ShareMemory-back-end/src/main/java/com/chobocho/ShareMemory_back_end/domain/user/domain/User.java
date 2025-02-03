package com.chobocho.ShareMemory_back_end.domain.user.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="tbl_user")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Builder
public class User {
    @Id
    private String userId;

    private String pwd;

    private String nickname;

    private LocalDate regDate;

    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;


}
