package com.chobocho.ShareMemory_back_end.domain.diary.repository;

import com.chobocho.ShareMemory_back_end.domain.diary.domain.Diary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
}
