package com.chobocho.ShareMemory_back_end.domain.comment.repository;

import com.chobocho.ShareMemory_back_end.domain.comment.domain.Comment;
import com.chobocho.ShareMemory_back_end.domain.diary.domain.Diary;
import org.hibernate.query.criteria.JpaParameterExpression;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("SELECT c FROM Comment c WHERE c.diary.dno = :dno")
    Page<Comment> findByDiaryId(@Param("dno")Long dno, Pageable pageable);

//    @Query("SELECT c FROM Comment c WHERE c.user.userId = :userId")
//    Comment findByUserId(@Param("userId")String userId);
}
