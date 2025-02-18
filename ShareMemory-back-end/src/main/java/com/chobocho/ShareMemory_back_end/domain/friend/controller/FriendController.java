package com.chobocho.ShareMemory_back_end.domain.friend.controller;

import com.chobocho.ShareMemory_back_end.domain.diary.dto.DiaryDTO;
import com.chobocho.ShareMemory_back_end.domain.friend.dto.FriendDTO;
import com.chobocho.ShareMemory_back_end.domain.friend.service.FriendService;
import com.chobocho.ShareMemory_back_end.domain.user.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/friend")
@RequiredArgsConstructor
public class FriendController {

    private final FriendService friendService;

    @PostMapping("/{toUserId}")
    public Long friendRequest(UserDTO fromUserDTO, @PathVariable String toUserId){
        return friendService.sendFriendRequest(fromUserDTO.getUserId(), toUserId);

    }

    @PatchMapping("/accept")
    public Map<String, String> accept(FriendDTO friendDTO) {
        return friendService.acceptFriendRequest(friendDTO.getFriendId());
    }

    @PatchMapping("/reject")
    public Map<String, String> reject(FriendDTO friendDTO) {
        return friendService.deniedFriendRequest(friendDTO.getFriendId());
    }

}
