package com.example.restcontroller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Member;
import com.example.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequiredArgsConstructor
public class RestHomeController {

    final JwtUtil jwtUtil; //컴포넌트 객체 생성
    final MemberRepository mRepository;   
    BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();
    final HttpSession httpSession;
    
    @PostMapping(value = "/login.json")
    public Map<String, Object> loginPOST(@RequestBody Member member){
        Map<String, Object> retMap = new HashMap<>();
        try {
            // 1. 아이디, 비번 전송 확인
            // log.info("로그인한_아이디비번 => {}", member.toString()); // 성공

            // 2. 이메일을 이용해서 정보를 가져옴.
            Member retmember = mRepository.findById(member.getMid()).orElse(null);

            // 3. 실패시 전송할 데이터
            retMap.put( "status", 0 );

            // 4. 암호가 일치하는지 확인 => 전송된 hash되지 않은 암호와 DB에 해시된 암호 일치 확인
            if(  bcpe.matches( member.getMpw(), retmember.getMpw()) ) {
                retMap.put( "status", 200 );
                retMap.put( "token", jwtUtil.createJwt(retmember.getMid(), retmember.getMname() ) );
            }
        }
        catch (Exception e) {
            e.printStackTrace(); 
            retMap.put( "status", -1 );
            retMap.put( "error", e.getMessage() );
        }
        return retMap;
    }
}
