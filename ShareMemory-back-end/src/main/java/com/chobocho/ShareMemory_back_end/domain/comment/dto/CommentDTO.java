package com.chobocho.ShareMemory_back_end.domain.comment.dto;

import com.chobocho.ShareMemory_back_end.domain.comment.domain.Comment;
import com.chobocho.ShareMemory_back_end.domain.comment.repository.CommentRepository;
import com.chobocho.ShareMemory_back_end.domain.diary.domain.Diary;
import com.chobocho.ShareMemory_back_end.domain.diary.repository.DiaryRepository;
import com.chobocho.ShareMemory_back_end.domain.user.domain.User;
import com.chobocho.ShareMemory_back_end.domain.user.repository.UserRepository;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@Builder
public class CommentDTO {



    private Long cno;

    private String userId;

    private Long dno;

    private String content;



}
