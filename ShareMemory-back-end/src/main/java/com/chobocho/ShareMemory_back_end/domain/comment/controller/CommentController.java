package com.chobocho.ShareMemory_back_end.domain.comment.controller;

import com.chobocho.ShareMemory_back_end.domain.comment.domain.Comment;
import com.chobocho.ShareMemory_back_end.domain.comment.dto.CommentDTO;
import com.chobocho.ShareMemory_back_end.domain.comment.repository.CommentRepository;
import com.chobocho.ShareMemory_back_end.domain.comment.service.CommentService;
import com.chobocho.ShareMemory_back_end.util.pagination.PageRequestDTO;
import com.chobocho.ShareMemory_back_end.util.pagination.PageResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/api/comment")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/create")
    public Comment createComment(CommentDTO commentDTO) {
        Comment comment = commentService.createComment(commentDTO);

        return comment;
    }

    @DeleteMapping("/{cno}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long cno) {
        commentService.deleteComment(cno);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{dno}")
    public ResponseEntity<PageResponseDTO<CommentDTO>> listAllComment(PageRequestDTO pageRequestDTO
            , @PathVariable Long dno){
        PageResponseDTO<CommentDTO> commentDTOs = commentService.listAllComment(pageRequestDTO, dno);

        return ResponseEntity.ok().build();

    }




}
