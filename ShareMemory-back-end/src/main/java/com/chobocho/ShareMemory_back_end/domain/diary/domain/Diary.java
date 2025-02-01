package com.chobocho.ShareMemory_back_end.domain.diary.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name="tbl_diary")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Getter
public class Diary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long dno;

    private String title;

    private String content;

    private LocalDate regDate;

    private int totalView;


}
