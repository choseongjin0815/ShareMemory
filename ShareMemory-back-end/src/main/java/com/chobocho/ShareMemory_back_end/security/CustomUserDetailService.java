package com.chobocho.ShareMemory_back_end.security;

import com.chobocho.ShareMemory_back_end.domain.user.domain.User;
import com.chobocho.ShareMemory_back_end.domain.user.dto.UserDTO;
import com.chobocho.ShareMemory_back_end.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        log.info("=============loadUserByUsername==================");

        User user = userRepository.findById(userId).orElse(null);

        if(user == null) {
            throw new UsernameNotFoundException("Not Found");
        }

        UserDTO userDTO = new UserDTO(
                user.getUserId(),
                user.getPwd(),
                user.getNickname(),
                user.getRegDate(),
                user.getUserStatus()
        );

        log.info(userDTO);

        return userDTO;
    }
}
