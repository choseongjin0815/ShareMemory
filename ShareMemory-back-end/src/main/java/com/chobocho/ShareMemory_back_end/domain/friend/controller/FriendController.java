package com.chobocho.ShareMemory_back_end.domain.friend.controller;

import com.chobocho.ShareMemory_back_end.domain.diary.dto.DiaryDTO;
import com.chobocho.ShareMemory_back_end.domain.friend.dto.FriendDTO;
import com.chobocho.ShareMemory_back_end.domain.friend.service.FriendService;
import com.chobocho.ShareMemory_back_end.domain.user.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Log4j2
@RestController
@RequestMapping("/api/friend")
@RequiredArgsConstructor
public class FriendController {

    private final FriendService friendService;

    @PostMapping("/{fromUserId}/{toUserId}")
    public Long friendRequest(@PathVariable String fromUserId, @PathVariable String toUserId){
        log.info(fromUserId);
        log.info(toUserId);
        return friendService.sendFriendRequest(fromUserId, toUserId);

    }

    @PatchMapping("/accept/{friendId}")
    public Map<String, String> accept(@PathVariable Long friendId) {
        return friendService.acceptFriendRequest(friendId);
    }

    @PatchMapping("/reject/{friendId}")
    public Map<String, String> reject(@PathVariable Long friendId) {
        return friendService.deniedFriendRequest(friendId);
    }

}
