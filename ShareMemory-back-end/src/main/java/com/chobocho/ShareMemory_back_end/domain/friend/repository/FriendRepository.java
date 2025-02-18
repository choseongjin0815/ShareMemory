package com.chobocho.ShareMemory_back_end.domain.friend.repository;

import com.chobocho.ShareMemory_back_end.domain.friend.domain.Friend;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendRepository extends JpaRepository<Friend, Long> {
}
