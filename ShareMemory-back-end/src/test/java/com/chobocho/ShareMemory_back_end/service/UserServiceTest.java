package com.chobocho.ShareMemory_back_end.service;

import com.chobocho.ShareMemory_back_end.domain.user.domain.User;
import com.chobocho.ShareMemory_back_end.domain.user.domain.UserStatus;
import com.chobocho.ShareMemory_back_end.domain.user.dto.UserDTO;
import com.chobocho.ShareMemory_back_end.domain.user.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.Collections;

@SpringBootTest
@Log4j2
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Test
    public void testGetUser() {
        UserDTO user = userService.get("user1");
        log.info(user);

    }

    @Test
    public void testRegistUser() {
        UserDTO userDTO = new UserDTO(
                "test",
                passwordEncoder.encode("1234"),
                "testNickname",
                LocalDate.now(),
                UserStatus.ACTIVE,
                Collections.emptyList()
        );

        userService.register(userDTO);
    }

}
