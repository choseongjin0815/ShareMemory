package com.chobocho.ShareMemory_back_end.domain.user.repository;

import com.chobocho.ShareMemory_back_end.domain.user.domain.User;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Transactional
public interface UserRepository extends JpaRepository<User, String> {

    Page<User> findByUserIdNot(String userId, Pageable pageable);

    @Query("SELECT u FROM User u " +
            "LEFT JOIN Friend f ON (f.toUserId = u OR f.fromUserId = u) " +
            "WHERE u.userId <> :userId AND f IS NULL")
    Page<User> findByUserIdNotFriends(@Param("userId") String userId, Pageable pageable);

    @Query("SELECT u FROM User u " +
            "LEFT JOIN Friend f ON (f.toUserId = u OR f.fromUserId = u) " +
            "WHERE f.friendStatus = 'FRIENDS' " +
            "AND u.userId <> :userId")
    Page<User> findByUserIdFriends(@Param("userId") String userId, Pageable pageable);

}
