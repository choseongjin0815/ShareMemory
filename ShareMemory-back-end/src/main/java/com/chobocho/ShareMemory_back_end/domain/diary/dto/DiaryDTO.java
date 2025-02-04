package com.chobocho.ShareMemory_back_end.domain.diary.dto;


import com.chobocho.ShareMemory_back_end.domain.diary.domain.Diary;
import com.chobocho.ShareMemory_back_end.domain.user.domain.User;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DiaryDTO {

    private long dno;

    private String title;

    private String content;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate regDate;

    private int totalView;

    private String userId;


    @Builder.Default
    private List<MultipartFile> files = new ArrayList<>();

    @Builder.Default
    private List<String> uploadFileNames = new ArrayList<>();

    public Diary dtoToEntity(User user){
        Diary diary = Diary.builder()
                .content(content)
                .regDate(regDate)
                .title(title)
                .totalView(totalView)
                .user(user)
                .build();

        List<String> uploadFileNames = this.uploadFileNames;

        if(uploadFileNames == null){
            return diary;
        }

        uploadFileNames.stream().forEach(uploadName -> {
            diary.addImageString(uploadName);
        });

        return diary;
    }

}
