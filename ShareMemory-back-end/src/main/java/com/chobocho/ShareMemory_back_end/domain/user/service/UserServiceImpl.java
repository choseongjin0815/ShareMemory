package com.chobocho.ShareMemory_back_end.domain.user.service;

import com.chobocho.ShareMemory_back_end.domain.diary.domain.Diary;
import com.chobocho.ShareMemory_back_end.domain.diary.dto.DiaryDTO;
import com.chobocho.ShareMemory_back_end.domain.user.domain.User;
import com.chobocho.ShareMemory_back_end.domain.user.domain.UserStatus;
import com.chobocho.ShareMemory_back_end.domain.user.dto.UserDTO;
import com.chobocho.ShareMemory_back_end.domain.user.repository.UserRepository;
import com.chobocho.ShareMemory_back_end.util.pagination.PageRequestDTO;
import com.chobocho.ShareMemory_back_end.util.pagination.PageResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public PageResponseDTO<UserDTO> getUserList(PageRequestDTO pageRequestDTO, String userId) {
        Pageable pageable =
                PageRequest.of(
                        pageRequestDTO.getPage() - 1,
                        pageRequestDTO.getSize(),
                        Sort.by("nickname").descending()
                );
        Page<User> result = userRepository.findByUserIdNot(userId, pageable);

        List<UserDTO> dtoList = result.getContent().stream()
                .map(user -> user.entityToDTO())
                .collect(Collectors.toList());

        long totalCount = result.getTotalElements();

        PageResponseDTO<UserDTO> responseDTO = PageResponseDTO.<UserDTO>withAll()
                .dtoList(dtoList)
                .pageRequestDTO(pageRequestDTO)
                .totalCount(totalCount)
                .build();

        return responseDTO;


    }
}
