package com.example.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class UrlFilter extends OncePerRequestFilter{
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            // ex) /ROOT/api/student2/update.json?id=q
            String contextPath = request.getContextPath();  // /ROOT/
            String path = request.getServletPath(); // /api/student2/update.json?id=q
            String query = request.getQueryString(); // id=q

            log.info("직전주소 => {},{},{}", contextPath, path, query); 
            // 직전주소 => /CUSTOM,/product/making.do,tno=1006&psno=1

            // url에 login, logout이 포함되지 않는 경우
            if(!path.contains("login") && !path.contains("logout")){
                // 로그인 세션 가져오기 
                HttpSession httpSession = request.getSession();

                // 만약 맨뒤에 쿼리(.do?no=777)가 없으면 (path가 없는 경우는 없다. home이나 그러려나.)
                if(query == null){
                    
                    httpSession.setAttribute("url", contextPath + path);
                }
                // 쿼리가 있으면
                else{
                    // 쿼리가 있을때 직전주소 url => httpSession저장하는 곳
                    httpSession.setAttribute("url", contextPath +  path + "?" +query);
                }
            }

            // 아래 명령어가 실행되어서 정상적인 컨트롤러로 진입가능
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
