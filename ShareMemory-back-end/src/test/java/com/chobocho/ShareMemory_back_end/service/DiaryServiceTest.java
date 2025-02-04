package com.chobocho.ShareMemory_back_end.service;

import com.chobocho.ShareMemory_back_end.domain.diary.domain.Diary;
import com.chobocho.ShareMemory_back_end.domain.diary.dto.DiaryDTO;
import com.chobocho.ShareMemory_back_end.domain.diary.service.DiaryService;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@Log4j2
@SpringBootTest
@Transactional
public class DiaryServiceTest {

    @Autowired
    private DiaryService diaryService;



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
}
