package com.chobocho.ShareMemory_back_end.domain.friend.service;

import com.chobocho.ShareMemory_back_end.domain.friend.domain.Friend;
import com.chobocho.ShareMemory_back_end.domain.friend.domain.FriendStatus;
import com.chobocho.ShareMemory_back_end.domain.friend.dto.FriendDTO;
import com.chobocho.ShareMemory_back_end.domain.friend.repository.FriendRepository;
import com.chobocho.ShareMemory_back_end.domain.user.domain.User;
import com.chobocho.ShareMemory_back_end.domain.user.repository.UserRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Map;

@Service
@Log4j2
@RequiredArgsConstructor
public class FriendServiceImpl implements FriendService{

    private final UserRepository userRepository;

    private final FriendRepository friendRepository;

    @Override
    public Long sendFriendRequest(String fromUserId, String toUserId) {
        User sender = userRepository.findById(fromUserId).orElse(null);
        User receiver = userRepository.findById(toUserId).orElse(null);

        FriendDTO friendDTO = FriendDTO.builder()
                .fromUserId(sender.getUserId())
                .toUserId(receiver.getUserId())
                .friendStatus(FriendStatus.WAITING)
                .regDate(LocalDate.now())
                .build();

        Friend friend = friendDTO.dtoToEntity(sender, receiver);

        Friend result = friendRepository.save(friend);

        return result.getFriendId();
    }

    @Override
    public Map<String, String> acceptFriendRequest(Long friendId) {
        Friend friend = friendRepository.findById(friendId).orElse(null);

        log.info(friend);

        friend.setFriendStatus(FriendStatus.FRIENDS);

        log.info(friend);

        log.info(friendRepository.save(friend));

        return Map.of("RESULT", "FRIEND_REQUEST_ACCEPTED");
    }

    @Override
    public Map<String, String> deniedFriendRequest(Long friendId) {
        Friend friend = friendRepository.findById(friendId).orElse(null);

        friend.setFriendStatus(FriendStatus.DENIED);

        friendRepository.save(friend);

        return Map.of("RESULT", "FRIEND_REQUEST_DENIED");
    }
}
