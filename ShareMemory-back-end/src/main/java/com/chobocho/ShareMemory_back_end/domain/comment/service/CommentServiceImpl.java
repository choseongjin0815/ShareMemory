package com.chobocho.ShareMemory_back_end.domain.comment.service;

import com.chobocho.ShareMemory_back_end.domain.comment.domain.Comment;
import com.chobocho.ShareMemory_back_end.domain.comment.dto.CommentDTO;
import com.chobocho.ShareMemory_back_end.domain.comment.repository.CommentRepository;
import com.chobocho.ShareMemory_back_end.domain.diary.domain.Diary;
import com.chobocho.ShareMemory_back_end.domain.diary.dto.DiaryDTO;
import com.chobocho.ShareMemory_back_end.domain.diary.repository.DiaryRepository;
import com.chobocho.ShareMemory_back_end.domain.user.domain.User;
import com.chobocho.ShareMemory_back_end.domain.user.repository.UserRepository;
import com.chobocho.ShareMemory_back_end.util.pagination.PageRequestDTO;
import com.chobocho.ShareMemory_back_end.util.pagination.PageResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final DiaryRepository diaryRepository;
    @Override
    public Comment createComment(CommentDTO commentDTO) {
        Diary diary = diaryRepository.findById(commentDTO.getDno()).orElse(null);
        User user = userRepository.findById(commentDTO.getUserId()).orElse(null);
        log.info(commentDTO);
        Comment comment = dtoToEntity(diary, user, commentDTO.getContent());

        return commentRepository.save(comment);
    }

    @Override
    public void deleteComment(Long cno) {
        Comment comment = commentRepository.findById(cno).orElse(null);

        commentRepository.delete(comment);
    }

    @Override
    public Comment modifyComment(CommentDTO commentDTO) {
        return null;
    }

    @Override
    public PageResponseDTO<CommentDTO> listAllComment(PageRequestDTO pageRequestDTO, Long dno) {
        Pageable pageable =
                PageRequest.of(
                        pageRequestDTO.getPage() - 1,
                        pageRequestDTO.getSize(),
                        Sort.by("cno").descending()
                );

        Page<Comment> result = commentRepository.findByDiaryId(dno, pageable);


        List<CommentDTO> dtoList = result.getContent().stream()
                .map(comment -> comment.toDTO())
                .collect(Collectors.toList());

        // 총 개수 가져오기
        long totalCount = result.getTotalElements();

        // PageResponseDTO 생성
        PageResponseDTO<CommentDTO> responseDTO = PageResponseDTO.<CommentDTO>withAll()
                .dtoList(dtoList)
                .pageRequestDTO(pageRequestDTO)
                .totalCount(totalCount)
                .build();


        return responseDTO;
    }

    @Override
    public Comment dtoToEntity(Diary diary, User user, String content) {
        return Comment.builder()
                .content(content)
                .diary(diary)
                .userId(user)
                .build();
    }
}
