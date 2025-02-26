package com.chobocho.ShareMemory_back_end.domain.user.service;

import com.chobocho.ShareMemory_back_end.domain.user.domain.User;
import com.chobocho.ShareMemory_back_end.domain.user.dto.UserDTO;
import com.chobocho.ShareMemory_back_end.util.pagination.PageRequestDTO;
import com.chobocho.ShareMemory_back_end.util.pagination.PageResponseDTO;

public interface UserService {
    //등록
    String register(UserDTO userDTO);
    //조회
    UserDTO get(String userId);

    //user의 상태를 INACTIVE로 변환
    User deleteUser(String userId);

    void modifyNickname(UserDTO userDTO);

    //나 제외 유저 목록 출력
    PageResponseDTO<UserDTO> getUserList(PageRequestDTO pageRequestDTO, String userId);

    //나와 친구 제외 유저 목록 출력
    PageResponseDTO<UserDTO> getNotFriendsUserList(PageRequestDTO pageRequestDTO, String userId);

    //친구 목록만 출력
    PageResponseDTO<UserDTO> getFriendList(PageRequestDTO pageRequestDTO, String userId);

}
