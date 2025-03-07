package com.chobocho.ShareMemory_back_end.domain.diary.service;

import com.chobocho.ShareMemory_back_end.domain.diary.domain.Diary;
import com.chobocho.ShareMemory_back_end.domain.diary.dto.DiaryDTO;
import com.chobocho.ShareMemory_back_end.util.pagination.PageRequestDTO;
import com.chobocho.ShareMemory_back_end.util.pagination.PageResponseDTO;

public interface DiaryService {

    Long register(DiaryDTO diaryDTO);

    DiaryDTO getDiary(Long dno);

    Diary deleteDiary(Long dno);

    void modifyDiary(DiaryDTO diaryDTO);

    PageResponseDTO<DiaryDTO> list(PageRequestDTO pageRequestDTO);

    PageResponseDTO<DiaryDTO> listLoginUser(PageRequestDTO pageRequestDTO, String userId);

    PageResponseDTO<DiaryDTO> listFriend(PageRequestDTO pageRequestDTO, String userId);

    //나를 toUser로 가지고 있는 케이스
    PageResponseDTO<DiaryDTO> listFriendToUser(PageRequestDTO pageRequestDTO, String userId);

    PageResponseDTO<DiaryDTO> listUserAndFriend(PageRequestDTO pageRequestDTO, String userId);
}
