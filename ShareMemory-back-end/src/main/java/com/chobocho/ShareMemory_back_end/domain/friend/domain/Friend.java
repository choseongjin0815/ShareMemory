package com.chobocho.ShareMemory_back_end.domain.friend.domain;

import com.chobocho.ShareMemory_back_end.domain.user.domain.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
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

    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private List<FriendStatus> friendStatusList = new ArrayList<>();

    private LocalDate regDate;
}
