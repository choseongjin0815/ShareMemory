package com.chobocho.ShareMemory_back_end.domain.diary.service;


import com.chobocho.ShareMemory_back_end.domain.diary.domain.Diary;
import com.chobocho.ShareMemory_back_end.domain.diary.dto.DiaryDTO;
import com.chobocho.ShareMemory_back_end.domain.diary.repository.DiaryRepository;
import com.chobocho.ShareMemory_back_end.domain.user.domain.User;
import com.chobocho.ShareMemory_back_end.domain.user.repository.UserRepository;
import com.chobocho.ShareMemory_back_end.util.pagination.PageRequestDTO;
import com.chobocho.ShareMemory_back_end.util.pagination.PageResponseDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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

        diaryDTO.setRegDate(LocalDate.now());

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

    @Override
    public PageResponseDTO<DiaryDTO> list(PageRequestDTO pageRequestDTO) {
        Pageable pageable =
                PageRequest.of(
                        pageRequestDTO.getPage() - 1,
                        pageRequestDTO.getSize(),
                        Sort.by("dno").descending()
                );
        Page<Diary> result = diaryRepository.findAll(pageable);

        List<DiaryDTO> dtoList = result.getContent().stream()
                .map(diary -> diary.toDTO())
                .collect(Collectors.toList());

         long totalCount = result.getTotalElements();

         PageResponseDTO<DiaryDTO> responseDTO = PageResponseDTO.<DiaryDTO>withAll()
                 .dtoList(dtoList)
                 .pageRequestDTO(pageRequestDTO)
                 .totalCount(totalCount)
                 .build();

         return responseDTO;

    }

    @Override
    public PageResponseDTO<DiaryDTO> listLoginUser(PageRequestDTO pageRequestDTO, String userId) {
        Pageable pageable =
                PageRequest.of(
                        pageRequestDTO.getPage() - 1,
                        pageRequestDTO.getSize(),
                        Sort.by("dno").descending()
                );

        Page<Diary> result = diaryRepository.findByUserId(userId, pageable);


        List<DiaryDTO> dtoList = result.getContent().stream()
                .map(diary -> diary.toDTO())
                .collect(Collectors.toList());

        // 총 개수 가져오기
        long totalCount = result.getTotalElements();

        // PageResponseDTO 생성
        PageResponseDTO<DiaryDTO> responseDTO = PageResponseDTO.<DiaryDTO>withAll()
                .dtoList(dtoList)
                .pageRequestDTO(pageRequestDTO)
                .totalCount(totalCount)
                .build();


        return responseDTO;
    }


   // 친구의 diary들만 출력
    @Override
    public PageResponseDTO<DiaryDTO> listFriend(PageRequestDTO pageRequestDTO, String userId) {
        Pageable pageable =
                PageRequest.of(
                        pageRequestDTO.getPage() - 1,
                        pageRequestDTO.getSize(),
                        Sort.by("dno").descending()
                );

        Page<Diary> result = diaryRepository.findDiaryByFromUser(userId, pageable);


        List<DiaryDTO> dtoList = result.getContent().stream()
                .map(diary -> diary.toDTO())
                .collect(Collectors.toList());

        // 총 개수 가져오기
        long totalCount = result.getTotalElements();

        // PageResponseDTO 생성
        PageResponseDTO<DiaryDTO> responseDTO = PageResponseDTO.<DiaryDTO>withAll()
                .dtoList(dtoList)
                .pageRequestDTO(pageRequestDTO)
                .totalCount(totalCount)
                .build();


        return responseDTO;
    }


    @Override
    public PageResponseDTO<DiaryDTO> listFriendToUser(PageRequestDTO pageRequestDTO, String userId){
        Pageable pageable = (
                PageRequest.of(
                        pageRequestDTO.getPage() - 1,
                        pageRequestDTO.getSize(),
                        Sort.by("dno").descending())
                );
         Page<Diary> result = diaryRepository.findDiaryByToUser(userId, pageable);

         List<DiaryDTO> dtoList = result.getContent().stream()
                 .map(diary -> diary.toDTO())
                 .collect(Collectors.toList());

         long totalCount = result.getTotalElements();

        // PageResponseDTO 생성
        PageResponseDTO<DiaryDTO> responseDTO = PageResponseDTO.<DiaryDTO>withAll()
                .dtoList(dtoList)
                .pageRequestDTO(pageRequestDTO)
                .totalCount(totalCount)
                .build();

        return responseDTO;
    }

    @Override
    public PageResponseDTO<DiaryDTO> listUserAndFriend(PageRequestDTO pageRequestDTO, String userId) {
        Pageable pageable =
                PageRequest.of(
                        pageRequestDTO.getPage() - 1,
                        pageRequestDTO.getSize(),
                        Sort.by("dno").descending()
                );

        Page<Diary> result = diaryRepository.findDiaryAll(userId, pageable);



        List<DiaryDTO> dtoList = result.getContent().stream()
                .map(diary -> diary.toDTO())
                .collect(Collectors.toList());

        // 총 개수 가져오기
        long totalCount = result.getTotalElements();

        // PageResponseDTO 생성
        PageResponseDTO<DiaryDTO> responseDTO = PageResponseDTO.<DiaryDTO>withAll()
                .dtoList(dtoList)
                .pageRequestDTO(pageRequestDTO)
                .totalCount(totalCount)
                .build();

        log.info(responseDTO);

        return responseDTO;
    }

}
