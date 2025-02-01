package com.chobocho.ShareMemory_back_end.domain.user.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name="tbl_user")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@ToString
public class User {
    @Id
    private String userId;

    private String pwd;

    private String nickname;

    private LocalDate regDate;


}
