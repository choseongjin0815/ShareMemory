package com.chobocho.ShareMemory_back_end.domain.user.domain;

import com.chobocho.ShareMemory_back_end.domain.user.dto.UserDTO;
import com.chobocho.ShareMemory_back_end.util.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name="tbl_user")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Builder
@Data
public class User extends BaseEntity {
    @Id
    private String userId;

    private String pwd;

    private String nickname;

    private LocalDate regDate;

    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;



    public UserDTO entityToDTO() {

        // 권한이 없으므로 빈 리스트 전달
        Collection<GrantedAuthority> authorities = Collections.emptyList();

        return new UserDTO(
                this.userId,
                this.pwd,
                this.nickname,
                this.regDate,
                this.userStatus,
                authorities
        );
    }



}
