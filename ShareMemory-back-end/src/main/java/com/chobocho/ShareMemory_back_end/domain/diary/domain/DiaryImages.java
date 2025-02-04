package com.chobocho.ShareMemory_back_end.domain.diary.domain;


import jakarta.persistence.Embeddable;
import jakarta.persistence.Table;
import lombok.*;

@Embeddable
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DiaryImages {
    private String fileName;

    private int ord;

    public void setOrd(int ord){
        this.ord = ord;
    }
}
