package com.chobocho.ShareMemory_back_end.security;

import com.chobocho.ShareMemory_back_end.domain.user.domain.User;
import com.chobocho.ShareMemory_back_end.domain.user.dto.UserDTO;
import com.chobocho.ShareMemory_back_end.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
@Log4j2
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("=============loadUserByUsername==================");

        log.info("userId : " + username);

        User user = userRepository.findById(username).orElse(null);

        log.info(user);

        if(user == null) {
            throw new UsernameNotFoundException("Not Found");
        }

        // 권한이 없으므로 빈 리스트 전달
        Collection<GrantedAuthority> authorities = Collections.emptyList();

        UserDTO userDTO = new UserDTO(
                user.getUserId(),
                user.getPwd(),
                user.getNickname(),
                user.getRegDate(),
                user.getUserStatus(),
                authorities
        );

        log.info(userDTO);

        return userDTO;
    }
}
