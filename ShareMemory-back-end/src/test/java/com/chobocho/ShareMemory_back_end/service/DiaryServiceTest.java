package com.chobocho.ShareMemory_back_end.service;

import com.chobocho.ShareMemory_back_end.domain.diary.domain.Diary;
import com.chobocho.ShareMemory_back_end.domain.diary.dto.DiaryDTO;
import com.chobocho.ShareMemory_back_end.domain.diary.repository.DiaryRepository;
import com.chobocho.ShareMemory_back_end.domain.diary.service.DiaryService;
import com.chobocho.ShareMemory_back_end.util.pagination.PageRequestDTO;
import com.chobocho.ShareMemory_back_end.util.pagination.PageResponseDTO;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@SpringBootTest
@Transactional
public class DiaryServiceTest {

    @Autowired
    private DiaryService diaryService;

    @Autowired
    private DiaryRepository diaryRepository;

    @Test
    public void diaryRegistTest(){
        DiaryDTO diaryDTO = DiaryDTO.builder()
                .content("testContent")
                .regDate(LocalDate.now())
                .title("testTitle")
                .userId("test")
                .totalView(1)
                .build();
        diaryService.register(diaryDTO);

    }

    @Test
    public void diaryGetTest() {
        DiaryDTO diaryDTO = diaryService.getDiary(2L);
        log.info(diaryDTO);
    }

    @Test
    public void diaryListTest() {
        PageRequestDTO pageRequestDTO = new PageRequestDTO();

        Pageable pageable =
                PageRequest.of(
                        pageRequestDTO.getPage() - 1,
                        pageRequestDTO.getSize(),
                        Sort.by("dno").descending()
                );
        Page<Diary> list = diaryRepository.findByUserId("user1", pageable);

        log.info(list);

        // Diary를 DiaryDTO로 변환
        List<DiaryDTO> dtoList = list.getContent().stream()
                .map(diary -> diary.toDTO())
                .collect(Collectors.toList());

        // 총 개수 가져오기
        long totalCount = list.getTotalElements();

        // PageResponseDTO 생성
        PageResponseDTO<DiaryDTO> responseDTO = PageResponseDTO.<DiaryDTO>withAll()
                .dtoList(dtoList)
                .pageRequestDTO(pageRequestDTO)
                .totalCount(totalCount)
                .build();

        log.info(responseDTO);
    }


    @Test
    public void diaryListFriendTest() {
        PageRequestDTO pageRequestDTO = new PageRequestDTO();

        Pageable pageable =
                PageRequest.of(
                        pageRequestDTO.getPage() - 1,
                        pageRequestDTO.getSize(),
                        Sort.by("dno").descending()
                );
        Page<Diary> list = diaryRepository.findDiaryByFromUser("user1", pageable);

        log.info(list);

        // Diary를 DiaryDTO로 변환
        List<DiaryDTO> dtoList = list.getContent().stream()
                .map(diary -> diary.toDTO())
                .collect(Collectors.toList());

        // 총 개수 가져오기
        long totalCount = list.getTotalElements();

        // PageResponseDTO 생성
        PageResponseDTO<DiaryDTO> responseDTO = PageResponseDTO.<DiaryDTO>withAll()
                .dtoList(dtoList)
                .pageRequestDTO(pageRequestDTO)
                .totalCount(totalCount)
                .build();

        log.info(responseDTO);
    }

}
