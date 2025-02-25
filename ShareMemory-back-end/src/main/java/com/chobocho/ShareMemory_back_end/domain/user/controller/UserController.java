package com.chobocho.ShareMemory_back_end.domain.user.controller;


import com.chobocho.ShareMemory_back_end.domain.user.dto.UserDTO;
import com.chobocho.ShareMemory_back_end.domain.user.service.UserService;
import com.chobocho.ShareMemory_back_end.util.pagination.PageRequestDTO;
import com.chobocho.ShareMemory_back_end.util.pagination.PageResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Log4j2
public class UserController {

    private final UserService userService;

    @GetMapping("/api/user/{userId}")
    public ResponseEntity<UserDTO> get(@PathVariable String userId) {
        UserDTO user = userService.get(userId);

        return ResponseEntity.ok(user);
    }

    @GetMapping("/api/user/{userId}/list")
    public ResponseEntity<PageResponseDTO<UserDTO>> getUserList(PageRequestDTO pageRequestDTO, @PathVariable String userId) {
        PageResponseDTO<UserDTO> userDTOs = userService.getUserList(pageRequestDTO, userId);

        return ResponseEntity.ok(userDTOs);
    }

    





}
