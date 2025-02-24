package com.chobocho.ShareMemory_back_end.domain.comment.domain;

import com.chobocho.ShareMemory_back_end.domain.comment.dto.CommentDTO;
import com.chobocho.ShareMemory_back_end.domain.diary.domain.Diary;
import com.chobocho.ShareMemory_back_end.domain.diary.domain.DiaryImages;
import com.chobocho.ShareMemory_back_end.domain.diary.dto.DiaryDTO;
import com.chobocho.ShareMemory_back_end.domain.user.domain.User;
import com.chobocho.ShareMemory_back_end.util.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="tbl_comment")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Builder
public class Comment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cno;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User userId;

    @ManyToOne
    @JoinColumn(name="diary_no")
    private Diary diary;

    private String content;


    public CommentDTO toDTO() {
        CommentDTO commentDTO = CommentDTO.builder()
                .cno(cno)
                .content(content)
                .dno(diary.getDno())
                .userId(userId.getUserId())
                .build();

        return commentDTO;
    }

}
