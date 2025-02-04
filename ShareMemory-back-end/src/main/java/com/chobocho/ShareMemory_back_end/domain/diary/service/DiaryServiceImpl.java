package com.chobocho.ShareMemory_back_end.domain.diary.service;


import com.chobocho.ShareMemory_back_end.domain.diary.domain.Diary;
import com.chobocho.ShareMemory_back_end.domain.diary.dto.DiaryDTO;
import com.chobocho.ShareMemory_back_end.domain.diary.repository.DiaryRepository;
import com.chobocho.ShareMemory_back_end.domain.user.domain.User;
import com.chobocho.ShareMemory_back_end.domain.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class DiaryServiceImpl implements DiaryService{
    private final DiaryRepository diaryRepository;
    private final UserRepository userRepository;

    @Override
    public Long register(DiaryDTO diaryDTO) {
        User user = userRepository.findById(diaryDTO.getUserId()).orElseThrow(() -> new IllegalArgumentException("user not found"));

        Diary diary = diaryDTO.dtoToEntity(user);

        Diary result = diaryRepository.save(diary);
        return result.getDno();
    }

    @Override
    public DiaryDTO getDiary(Long dno) {
        Diary diary = diaryRepository.findById(dno).orElseThrow(() -> new IllegalArgumentException("not found diary"));
        DiaryDTO diaryDTO = diary.toDTO();
        return diaryDTO;
    }

    @Override
    public Diary deleteDiary(Long dno){
        Diary diary = diaryRepository.findById(dno).orElse(null);
        if(diary == null) {
            return null;
        }
        diaryRepository.delete(diary);
        return diary;
    }

    @Override
    public void modifyDiary(DiaryDTO diaryDTO){
        Diary diary = diaryRepository.findById(diaryDTO.getDno()).orElseThrow(() -> new IllegalArgumentException());


        if(diaryDTO.getContent() != null) {
            diary.setContent(diaryDTO.getContent());
        }
        if(diaryDTO.getTitle() != null) {
            diary.setTitle(diaryDTO.getTitle());
        }
        //업로드 된 파일을 우선 지움
        diary.clearList();

        List<String> uploadFileNames = diaryDTO.getUploadFileNames();

        if(uploadFileNames != null && uploadFileNames.size() > 0) {
            uploadFileNames.forEach(uploadName -> {
                diary.addImageString(uploadName);
            });
        }
        diaryRepository.save(diary);
    }
}
