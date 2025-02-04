package com.chobocho.ShareMemory_back_end.domain.diary.controller;

import com.chobocho.ShareMemory_back_end.domain.diary.domain.Diary;
import com.chobocho.ShareMemory_back_end.domain.diary.dto.DiaryDTO;
import com.chobocho.ShareMemory_back_end.domain.diary.service.DiaryService;
import com.chobocho.ShareMemory_back_end.util.CustomFileUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/api/diary")
public class DiaryController {

    private final DiaryService diaryService;
    private final CustomFileUtil customFileUtil;

    @PostMapping("/")
    public Map<String, Long> register(DiaryDTO diaryDTO) {
        log.info("register" + diaryDTO);
        List<MultipartFile> files = diaryDTO.getFiles();

        List<String> uploadFileNames = customFileUtil.saveFiles(files);

        diaryDTO.setUploadFileNames(uploadFileNames);

        log.info(uploadFileNames);

        //서비스 호출
        Long dno = diaryService.register(diaryDTO);

        try{
            Thread.sleep(1000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return Map.of("result", dno);
    }

    @GetMapping("/{dno}")
    public DiaryDTO get(@PathVariable Long dno){

        return diaryService.getDiary(dno);
    }

    @GetMapping("/view/{fileName}")
    public ResponseEntity<Resource> viewFileGet(@PathVariable String fileName){
        return customFileUtil.getFile(fileName);
    }

    @DeleteMapping("/{dno}")
    public Map<String, String> delete(@PathVariable Long dno){
        List<String> oldFileNames = diaryService.getDiary(dno).getUploadFileNames();

        diaryService.deleteDiary(dno);

        customFileUtil.deleteFiles(oldFileNames);

        return Map.of("RESULT", "SUCCESS");
    }

    @PutMapping("/{dno}")
    public Map<String, String> modify(@PathVariable Long dno, DiaryDTO diaryDTO){
        diaryDTO.setDno(dno);

        DiaryDTO oldDiaryDTO = diaryService.getDiary(dno);

        //기존 데이터베이스에 존재하는 파일들
        List<String> oldFileNames = oldDiaryDTO.getUploadFileNames();

        //새로 업로드 해야하는 파일
        List<MultipartFile> files = diaryDTO.getFiles();

        //새로 업로드 될 파일 이름들
        List<String> currentUploadFileNames = customFileUtil.saveFiles(files);

        //화면에서 유지할 파일들
        List<String> uploadFileNames = diaryDTO.getUploadFileNames();

        //유지될 파일들 + 새로만든 파일 이름들
        if(currentUploadFileNames != null && currentUploadFileNames.size() > 0) {
            uploadFileNames.addAll(currentUploadFileNames);
        }

        diaryService.modifyDiary(diaryDTO);

        if(oldFileNames != null && oldFileNames.size() > 0){
            //지울 파일 목록 찾기
            //예전 파일 이름 중에서 지워져야 할 파일 이름들
            //기존에 있던 파일 이름들 중에 새로 업로드될 파일 이름에 없는 파일들 remove
            List<String> removeFiles = oldFileNames
                    .stream()
                    .filter(fileName -> uploadFileNames.indexOf(fileName) == -1).collect(Collectors.toList());

            customFileUtil.deleteFiles(removeFiles);
        }
        return Map.of("RESULT", "SUCCESS");
    }}
