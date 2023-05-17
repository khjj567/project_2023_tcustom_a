package com.example.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.entity.Member;
import com.example.restcontroller.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtFilter extends OncePerRequestFilter{

    final JwtUtil jwtUtil;
    ObjectMapper objectMapper = new ObjectMapper(); // json으로 변환하는 라이브러리
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            Map<String, Object> retMap = new HashMap<>();

            String token = request.getHeader("token");
            if(token == null) {  //{status:-1, message:"token null"} 로 반환됨.
                retMap.put("status", -1);
                retMap.put("message" , "token null");
                String result = objectMapper.writeValueAsString(retMap);
                response.getWriter().write(result);
                return;  //메소드 종료
            } 

            if( token.length() <= 0 ) { //{status:-1, message:"token is empty"} 로 반환됨.
                retMap.put("status", -1);
                retMap.put("message" , "token is empty");
                String result = objectMapper.writeValueAsString(retMap);
                response.getWriter().write(result);
                return; // 메소드 종료
            }
            
            Member obj = jwtUtil.checkJwt(token);
            log.info("고객obj=> {}", obj);
            if(obj == null) {
                
                retMap.put("status", -1);
                retMap.put("message" , "token error");
                String result = objectMapper.writeValueAsString(retMap);
                response.getWriter().write(result);
                return; // 메소드 종료
            }

            // 아래 명령어가 실행되어가 정상적인 컨트롤러로 진입가능.
            filterChain.doFilter(request, response);
        }
        catch (Exception e) {
            e.printStackTrace();
            response.sendError(-1, "token error");
        }
    }
    
}
