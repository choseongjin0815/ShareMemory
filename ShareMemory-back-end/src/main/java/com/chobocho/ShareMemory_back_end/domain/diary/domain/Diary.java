package com.chobocho.ShareMemory_back_end.domain.diary.domain;

import com.chobocho.ShareMemory_back_end.domain.user.domain.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="tbl_diary")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Getter
public class Diary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long dno;

    private String title;

    private String content;

    private LocalDate regDate;

    private int totalView;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

}
