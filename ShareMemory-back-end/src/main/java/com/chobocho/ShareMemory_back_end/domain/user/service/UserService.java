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

    PageResponseDTO<UserDTO> getUserList(PageRequestDTO pageRequestDTO, String userId);



}
