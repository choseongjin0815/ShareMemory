package com.chobocho.ShareMemory_back_end.domain.diary.repository;

import com.chobocho.ShareMemory_back_end.domain.diary.domain.Diary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DiaryRepository extends JpaRepository<Diary, Long> {

    //로그인된 유저에 해당하는 일기 출력
    @Query("SELECT d FROM Diary d WHERE d.user.userId = :userId")
    Page<Diary> findByUserId(@Param("userId")String userId, Pageable pageable);



    @Query("SELECT d FROM Diary d " +
            "INNER JOIN Friend f ON d.user.userId = f.toUserId.userId " +
            "WHERE f.fromUserId.userId = :userId")
    Page<Diary> findDiaryByFromUser(@Param("userId") String userId, Pageable pageable);



    @Query("SELECT distinct d FROM Diary d " +
            "JOIN Friend f ON (d.user.userId = f.toUserId.userId OR d.user.userId = f.fromUserId.userId) " +
            "WHERE (f.fromUserId.userId = :userId OR f.toUserId.userId = :userId) " +
            "OR d.user.userId = :userId ")
    Page<Diary> findDiaryAll(@Param("userId") String userId, Pageable pageable);
}
