package com.example.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.handler.MemberLoginSuccessHandler;
import com.example.handler.MemberLogoutSuccessHandler;
import com.example.service.SecurityServiceMemberImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Configuration // 환경설정파일이다. 서버가 구동되기 전에 호출된다.
@EnableWebSecurity // 시큐리티를 사용
@Slf4j
@RequiredArgsConstructor
public class SecurityConfig {

    final SecurityServiceMemberImpl memberDetailsService; // member 테이블과 연동되는 서비스
    // final SecurityServiceImpl2 student2DetailsService1; // student2 테이블과 연동되는 서비스

    @Bean 
    @Order(value = 1)
    public SecurityFilterChain filterChain1(HttpSecurity http) throws Exception{ //http: 변수
        log.info("SecurityConfig => {}", "start filer chain1");

        // 권한설정
        http.authorizeRequests()
            .antMatchers("/join.do").permitAll() // 고객join까지 막히는 불상사를 막기 위해 permitAll
            // .antMatchers("/").permitAll()
            
            // .antMatchers("/seller", "/seller/*").hasAuthority("ROLE_SELLER")
            .antMatchers("/member","/member/*").hasAuthority("ROLE_MEMBER") // 주소가 9090/ROOT/admin ~~ 모든 것
            // .antMatchers("/","/home/*").hasAnyAuthority("ROLE_NO")
            .anyRequest().permitAll(); // 풀어놔서 로그인은 들어갈 수 있음 // 지정해주지 않아도 되는건가요?
            
            // 이후에 고객으로 로그인 하고 seller/home.do 페이지로 가면
            // "There was an unexpected error (type=Forbidden, status=403)."" : 접근권한 없음 /권한 때문에 거절되었다는 것

            // 403 페이지 설정(접근권한 불가시 표시할 화면)
            http.exceptionHandling().accessDeniedPage("/403page.do");

        // 로그인처리
        // home에 하나만 있어야함
        http.formLogin()
            .loginPage("/login.do") // 로그인은 무슨 화면을 쓸거야?(get)
            .loginProcessingUrl("/loginaction.do") // 로그인 action은 어디로 보내?(post)
            .usernameParameter("mid") // id의 name값은 뭐야? 
            .passwordParameter("mpw") // password의 name값은 뭐야? 
            .successHandler(new MemberLoginSuccessHandler()) // 특정 타입만 들어갈 수 있음 : AuthenticationSuccessHandler
            .defaultSuccessUrl("/home.do") // 로그인 성공시 이동할 페이지
            .permitAll();

            // 로그아웃 처리(GET안됨 POST로 처리)
            // home에 하나만 있어야함
            http.logout()
            .logoutUrl("/logout.do") // 로그아웃은 로그아웃.do에서 할거에요
            .logoutSuccessHandler(new MemberLogoutSuccessHandler())
            .invalidateHttpSession(true) // 권한 날리기
            .clearAuthentication(true) // 권한 날리기
            .permitAll();

            // post는 csrf를 전송해야하지만, 주소가 /api로 시작하는 주소는 csrf가 없어도 허용하도록 설정
            http.csrf().ignoringAntMatchers("/api/**");

            // 서비스등록
            http.userDetailsService(memberDetailsService); 
            return http.build();
    }

    // 필터규칙을 적용하지 않도록 설정하기 (in 정적자원에서)
    // resources/static은 시큐리티를 적용받지 않음
    @Bean 
    public WebSecurityCustomizer webSecurityCustomizer(){
        return (web) -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
        // PathRequest 임포트 잘못하면 오류뜬다 // 만약 안되면 다른걸로 해보기
    }
    
    // 회원가입에서 사용했던 암호와 알고리즘 정의 (로그인할때 같은 것을 써야하니까)
    @Bean // 서버 구동시 자동으로 실행됨 => @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
