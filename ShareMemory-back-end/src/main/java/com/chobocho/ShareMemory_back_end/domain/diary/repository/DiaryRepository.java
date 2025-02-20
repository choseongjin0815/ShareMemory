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


    //fromUserId 기준 친구
    @Query("SELECT d FROM Diary d " +
            "INNER JOIN Friend f ON d.user.userId = f.toUserId.userId " +
            "WHERE f.fromUserId.userId = :userId " +
            "AND f.friendStatus = 'FRIENDS' ")
    Page<Diary> findDiaryByFromUser(@Param("userId") String userId, Pageable pageable);


    //toUserId 기준 친구 user를 toUserId로 가지고 있는 경우
    @Query("SELECT d FROM Diary d " +
            "INNER JOIN Friend f ON d.user.userId = f.fromUserId.userId " +
            "WHERE f.toUserId.userId = :userId " +
            "AND f.friendStatus = 'FRIENDS' ")
    Page<Diary> findDiaryByToUser(@Param("userId") String userId, Pageable pageable);

    //친구와 나의 리스트들
    @Query("SELECT distinct d FROM Diary d " +
            "JOIN Friend f ON (d.user.userId = f.toUserId.userId OR d.user.userId = f.fromUserId.userId) " +
            "WHERE (f.fromUserId.userId = :userId " +
            "OR f.toUserId.userId = :userId " +
            "OR d.user.userId = :userId)" +
            "AND f.friendStatus = 'FRIENDS' ")
    Page<Diary> findDiaryAll(@Param("userId") String userId, Pageable pageable);
}
