package com.chobocho.ShareMemory_back_end.domain.friend.domain;

import com.chobocho.ShareMemory_back_end.domain.user.domain.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="tbl_friend")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Friend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long friendId;

    @ManyToOne
    @JoinColumn(name="from_user_id")
    private User fromUserId;

    @ManyToOne
    @JoinColumn(name="to_user_id")
    private User toUserId;

    @Enumerated(EnumType.STRING)
    private FriendStatus friendStatus;

    private LocalDate regDate;
}
