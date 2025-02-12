package com.chobocho.ShareMemory_back_end.repository;

import com.chobocho.ShareMemory_back_end.domain.user.domain.User;
import com.chobocho.ShareMemory_back_end.domain.user.domain.UserStatus;
import com.chobocho.ShareMemory_back_end.domain.user.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

@SpringBootTest
@Log4j2
public class UserRepositoryTest {

    @Autowired
    private PasswordEncoder passwordEncoder;
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

    @Test
    public void encodePassword() {
        for(int i = 0; i < 4; i++){
            log.info("user"+i+1);
            User user = userRepository.findById("user"+(i+1)).orElse(null);
            user.setPwd(passwordEncoder.encode("pass"+(i+1)));
            userRepository.save(user);
        }
    }
}
