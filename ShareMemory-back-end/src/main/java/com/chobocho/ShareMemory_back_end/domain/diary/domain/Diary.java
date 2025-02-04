package com.chobocho.ShareMemory_back_end.domain.diary.domain;

import com.chobocho.ShareMemory_back_end.domain.diary.dto.DiaryDTO;
import com.chobocho.ShareMemory_back_end.domain.diary.service.DiaryService;
import com.chobocho.ShareMemory_back_end.domain.user.domain.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="tbl_diary")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Getter
@Setter
public class Diary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long dno;

    private String title;

    private String content;

    private LocalDate regDate;

    private int totalView;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ElementCollection
    @Builder.Default
    List<DiaryImages> imageList = new ArrayList<>();


    public void addImage(DiaryImages image) {

        image.setOrd(this.imageList.size());
        imageList.add(image);
    }

    public void addImageString(String fileName){

        DiaryImages productImage = DiaryImages.builder()
                .fileName(fileName)
                .build();
        addImage(productImage);

    }

    public DiaryDTO toDTO() {
        DiaryDTO diaryDTO = DiaryDTO.builder()
                .userId(user.getUserId())
                .totalView(totalView)
                .content(content)
                .regDate(regDate)
                .title(title)
                .dno(dno)
                .build();
        List<DiaryImages> imageList = this.imageList;

        if(imageList == null || imageList.size() == 0 ){
            return diaryDTO;
        }

        List<String> fileNameList = imageList.stream().map(productImage ->
                productImage.getFileName()).toList();

        diaryDTO.setUploadFileNames(fileNameList);

        return diaryDTO;
    }

    public void clearList() {
        this.imageList.clear();
    }


}
