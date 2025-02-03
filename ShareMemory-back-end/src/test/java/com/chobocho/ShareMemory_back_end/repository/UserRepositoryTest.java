package com.chobocho.ShareMemory_back_end.repository;

import com.chobocho.ShareMemory_back_end.domain.user.domain.User;
import com.chobocho.ShareMemory_back_end.domain.user.domain.UserStatus;
import com.chobocho.ShareMemory_back_end.domain.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testRegister(){
        User user = User.builder()
                .userId("test")
                .nickname("testNickname")
                .pwd("testPwd")
                .regDate(LocalDate.now())
                .userStatus(UserStatus.ACTIVE)
                .build();
        userRepository.save(user);
    }
}
