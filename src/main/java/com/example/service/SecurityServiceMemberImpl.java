package com.example.service;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.dto.MemberUser;
import com.example.entity.Member;
import com.example.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class SecurityServiceMemberImpl implements UserDetailsService{

    final String format = "시큐리티Member_IMPL => {}";
    final MemberRepository mRepository;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info(format, username); 
        Member obj = mRepository.findById(username).orElse(null);
        log.info(format, obj); 
        if(obj != null) { // 가져올 정보가 있다. 존재하는 아이디가 있다.
            log.info(format, obj);
            // return User.builder()
            //     .username(obj.getMid())
            //     .password(obj.getMpw())
            //     .roles("MEMBER").build();

            String[] strRole = {"ROLE_MEMBER"};
            Collection<GrantedAuthority> role = AuthorityUtils.createAuthorityList(strRole);
            return new MemberUser(obj.getMid(), obj.getMpw(), role, obj.getMname());
            // User -> dto>MemberUser를 만들어서 사용한다
            // "ROLE_MEMBER" : 권한은 원래 User에 존하는 구성요소로 반드시 필요하다
            // "ROLE_"+member.getRole() 다음과 같이 넣지만, 없으면 임의의 string을 넣어줘야한다.
        }

        // 이메일이 없는 경우
        return User.builder().username("_").password("_").roles("_").build();
    }
    
}
