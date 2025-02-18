package com.chobocho.ShareMemory_back_end.domain.friend.service;

import com.chobocho.ShareMemory_back_end.domain.friend.dto.FriendDTO;

import java.util.Map;

public interface FriendService {

    //보낼 때 상태 요청 중
    Long sendFriendRequest(String fromUserId, String toUserId);

    //친구 요청 승낙
    Map<String, String> acceptFriendRequest(Long friendId);

    //친구 요청 거절
    Map<String, String> deniedFriendRequest(Long friendId);


}
