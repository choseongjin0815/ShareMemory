package com.chobocho.ShareMemory_back_end.domain.diary.repository;

import com.chobocho.ShareMemory_back_end.domain.diary.domain.Diary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DiaryRepository extends JpaRepository<Diary, Long> {

    //로그인된 유저에 해당하는 일기 출력
    @Query("select d from Diary d where d.user.userId = :userId")
    Page<Diary> findByUserId(@Param("userId")String userId, Pageable pageable);

}
