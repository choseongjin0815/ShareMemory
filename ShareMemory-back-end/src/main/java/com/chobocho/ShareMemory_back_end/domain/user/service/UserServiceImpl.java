package com.chobocho.ShareMemory_back_end.domain.user.service;

import com.chobocho.ShareMemory_back_end.domain.user.domain.User;
import com.chobocho.ShareMemory_back_end.domain.user.domain.UserStatus;
import com.chobocho.ShareMemory_back_end.domain.user.dto.UserDTO;
import com.chobocho.ShareMemory_back_end.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    @Override
    public String register(UserDTO userDTO) {
        log.info(userDTO);
        User user = userDTO.toEntity();
        log.info(user);
        User registeredUser = userRepository.save(user);
        return registeredUser.getUserId();
    }

    @Override
    public User deleteUser(String userId) {
        User user = userRepository.findById(userId).orElse(null);

        user.setUserStatus(UserStatus.INACTIVE);

        return userRepository.save(user);
    }

    @Override
    public UserDTO get(String userId) {
        User user = userRepository.findById(userId).orElse(null);

        UserDTO userDTO = user.entityToDTO();
        return userDTO;
    }

    @Override
    public void modifyNickname(UserDTO userDTO) {
        User user = userRepository.findById(userDTO.getUserId()).orElse(null);
        user.setNickname(userDTO.getNickname());
        userRepository.save(user);
    }
}
