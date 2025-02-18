package com.chobocho.ShareMemory_back_end.service;

import com.chobocho.ShareMemory_back_end.domain.friend.domain.Friend;
import com.chobocho.ShareMemory_back_end.domain.friend.service.FriendService;
import com.chobocho.ShareMemory_back_end.domain.user.dto.UserDTO;
import jakarta.transaction.Transactional;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

@SpringBootTest
@Log4j2
public class FriendServiceTest {
    @Autowired
    private FriendService friendService;

    @Test
    public void testFriendRequest() {
        friendService.sendFriendRequest("user1", "user3");
    }

    @Test
    public void testAcceptFriendRequest() {
        friendService.acceptFriendRequest(1L);
    }

    @Test
    public void testRejectFriendRequest() {
        friendService.deniedFriendRequest(1L);
    }




}
