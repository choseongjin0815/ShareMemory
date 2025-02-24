package com.chobocho.ShareMemory_back_end.domain.comment.service;

import com.chobocho.ShareMemory_back_end.domain.comment.domain.Comment;
import com.chobocho.ShareMemory_back_end.domain.comment.dto.CommentDTO;
import com.chobocho.ShareMemory_back_end.domain.diary.domain.Diary;
import com.chobocho.ShareMemory_back_end.domain.user.domain.User;
import com.chobocho.ShareMemory_back_end.util.pagination.PageRequestDTO;
import com.chobocho.ShareMemory_back_end.util.pagination.PageResponseDTO;

public interface CommentService {

    Comment createComment (CommentDTO commentDTO);

    void deleteComment(Long cno);

    Comment modifyComment(CommentDTO commentDTO);

    PageResponseDTO<CommentDTO> listAllComment(PageRequestDTO pageRequestDTO, Long dno);

    Comment dtoToEntity(Diary diary, User user, String content);
}
