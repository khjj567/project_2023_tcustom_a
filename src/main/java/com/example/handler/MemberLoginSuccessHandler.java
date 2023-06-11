package com.example.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MemberLoginSuccessHandler implements AuthenticationSuccessHandler{
    
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
                log.info("로그인성공핸들러 => {}", authentication.toString() );
            // 로그인성공핸들러 => UsernamePasswordAuthenticationToken [Principal=org.springframework.security.core.userdetails.User [Username=abb, Password=[PROTECTED], Enabled=true, AccountNonExpired=true, credentialsNonExpired=true, AccountNonLocked=true, Granted Authorities=[ROLE_CUSTOMER]], Credentials=[PROTECTED], Authenticated=true, Details=WebAuthenticationDetails [RemoteIpAddress=127.0.0.1, SessionId=a72ca593-843d-4a25-80e6-58dd2859825f], Granted Authorities=[ROLE_CUSTOMER]]
            String role = authentication.getAuthorities().toArray()[0].toString();
            log.info("로그인성공핸들러_role => {}", role );

            // 여기서 이전페이지 정보를 가져옴
            HttpSession httpSession = request.getSession();
            // "url"이 맞음 : UrlFilter에서 "url"로 만들었음 그리고 세션에 넣음
            String backUrl = (String) httpSession.getAttribute("url");
                log.info("이전주소=>{}", backUrl);

            if(backUrl != null){
                if(role.equals("ROLE_MEMBER")){ // 권한이 고객일때
                    response.sendRedirect(backUrl);
                }
                else{ // 위의 권한이 아닌 경우
                    response.sendRedirect(request.getContextPath() + "/home.do");
                }
            }
            else{
                response.sendRedirect(request.getContextPath() + "/home.do");
            }
            
        }
    }
    
