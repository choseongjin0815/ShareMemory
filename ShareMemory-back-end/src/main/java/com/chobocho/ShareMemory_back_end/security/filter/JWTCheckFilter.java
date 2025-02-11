package com.chobocho.ShareMemory_back_end.security.filter;


import com.chobocho.ShareMemory_back_end.domain.user.domain.UserStatus;
import com.chobocho.ShareMemory_back_end.domain.user.dto.UserDTO;
import com.chobocho.ShareMemory_back_end.util.jwt.JWTUtil;
import com.google.gson.Gson;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

@Log4j2
public class JWTCheckFilter extends OncePerRequestFilter {

    //filter를 적용하지 않을 조건들
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        // Preflight요청은 체크하지 않음 브라우저가 먼저 서버에 보내는 요청
        if(request.getMethod().equals("OPTIONS")){
            return true;
        }

        String path = request.getRequestURI();

        log.info("check uri........." + path);

        //경로가 user인 경우 filter 제외
        if(path.startsWith("/api/user/")){
            return true;
        }

        //이미지 조회 테스트용 경로는 제외
        if(path.startsWith("/api/diary/view/")) {
            return true;
        }

        //return false면 filter가 적용됨
        return false;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        log.info("=================JWT CheckFilter====================");

        String authHeaderStr = request.getHeader("Authorization");

        try {
            //bearer 7자리 제외하고 토큰 부분만 추출, null값이면 nullPointerException
            String accessTokne = authHeaderStr.substring(7);

            Map<String, Object> claims = JWTUtil.validateToken(accessTokne);

            log.info("JWT Claims : " + claims);

            String userId = (String)claims.get("userId");
            String pwd = (String)claims.get("pwd");
            String nickname = (String)claims.get("nickname");
            LocalDate regDate = (LocalDate)claims.get("regDate");
            UserStatus userStatus = (UserStatus)claims.get("userStatus");

            Collection<GrantedAuthority> authorities = Collections.emptyList();


            UserDTO userDTO = new UserDTO(userId, pwd, nickname, regDate, userStatus, authorities);

            log.info("============================");
            log.info(userDTO);
            log.info("============================");



            // 객체는 사용자의 인증 정보를 담고 있는 객체로, 인증과 권한 정보를 포함하여 Spring Security에서 인증을 처리하는 데 사용
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(userDTO, pwd, userDTO.getAuthorities());


            //인증 토큰(authenticationToken)을 SecurityContext에 설정하여, 현재 요청에서 인증된 사용자 정보를 유지
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            filterChain.doFilter(request, response);
        } catch(Exception e) {
            log.error("JWT Check Error..............");
            log.error(e.getMessage());

            Gson gson = new Gson();
            String msg = gson.toJson(Map.of("error", "ERROR_ACCESS_TOKEN"));

            response.setContentType("application/json");
            PrintWriter printWriter = response.getWriter();
            printWriter.println(msg);
            printWriter.close();
        }


    }
}
