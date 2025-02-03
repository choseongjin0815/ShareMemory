package com.chobocho.ShareMemory_back_end.domain.user.service;

import com.chobocho.ShareMemory_back_end.domain.user.domain.User;
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
    public UserDTO get(String userId) {
        return null;
    }
}
