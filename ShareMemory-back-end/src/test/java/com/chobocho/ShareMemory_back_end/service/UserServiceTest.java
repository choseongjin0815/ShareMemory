package com.chobocho.ShareMemory_back_end.service;

import com.chobocho.ShareMemory_back_end.domain.user.domain.User;
import com.chobocho.ShareMemory_back_end.domain.user.domain.UserStatus;
import com.chobocho.ShareMemory_back_end.domain.user.dto.UserDTO;
import com.chobocho.ShareMemory_back_end.domain.user.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
@Log4j2
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void testRegisterService() {
        UserDTO userDTO = UserDTO.builder()
                .userId("user1")
                .nickname("userNickname1")
                .pwd("testpwd1")
                .userStatus(UserStatus.ACTIVE)
                .regDate(LocalDate.now())
                .build();

        log.info(userDTO);
        userService.register(userDTO);
    }

}
