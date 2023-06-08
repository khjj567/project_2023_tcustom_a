package com.example.controller;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.dto.MemberUser;
import com.example.dto.TshirtProductSorting;
import com.example.entity.Member;
import com.example.entity.TshirtImage;
import com.example.entity.TshirtView4;
import com.example.repository.MemberRepository;
import com.example.repository.TshirtImageRepository;
import com.example.repository.TshirtRepository;
import com.example.repository.TshirtView1Repository;
import com.example.repository.TshirtView2Repository;
import com.example.repository.TshirtView4Repository;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j

@RequiredArgsConstructor
public class HomeController {

    final MemberRepository mRepository;
    final TshirtView1Repository tv1Repository;
    final TshirtView2Repository tv2Repository;
    final TshirtView4Repository tv4Repository;
    BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();
    final TshirtImageRepository tiRepository;
    final TshirtRepository tRepository;
    final ResourceLoader resourceLoader; 
    final HttpSession httpSession;   

    //127.0.0.1:9090/CUSTOM/home.do
    @GetMapping(value = "/home.do")  
    public String homeGET(Model model, @AuthenticationPrincipal MemberUser user, HttpSession session){
        if(user != null){ // 로그인 되었음
        log.info("로그인user => {}", user); 
        //로그인user => MemberUser(username=aaa, authorities=[ROLE_MEMBER], name=aaa)
        }
        
        model.addAttribute("user", user);

        return "home";
    } // OK : 05/17

    
    // 127.0.0.1:9090/CUSTOM/join.do
    @GetMapping(value = "/join.do")
    public String joinGET(){
        try{
            return "join";
        }
        catch(Exception e){
            e.printStackTrace();
            return "redirect:/home.do";
        }
    } // OK : 05/17

    // 127.0.0.1:9090/CUSTOM/join.do
    @PostMapping(value="/join.do")
    public String joinPOST(@ModelAttribute Member member) {
        try{
            // log.info("회원가입입력정보=> {}", member.toString()); //OK 
            
            // 암호화는 bcpe를 이용해 암호화하기
            // 가입하는 학생이 입력한 password를 가져와서 bcpe로 encoding한 뒤 DB의 password에 set한다
            member.setMpw(bcpe.encode(member.getMpw()));
            // log.info("암호화완료 => {}", member.toString()); //OK 
            mRepository.save(member);
            return "redirect:/login.do";
        }
        catch(Exception e){
            e.printStackTrace();
            return "redirect:/home.do";
        } //OK : 05/17
    }

    // 127.0.0.1:9090/CUSTOM/login.do
    @GetMapping(value = "/login.do")
    public String loginGET(){
        try{
            return "login";
        }
        catch(Exception e){
            e.printStackTrace();
            return "redirect:/home.do";
        }
    } // OK : 5/17
    // SecurityConfig에 비밀번호 인코딩 설정 안해놔서 오류
    // html에서는 post로 보내고, controller에서는 get만 쓴다. 로그인 하는 post는 SecurityConfig에서 처리한다.

    // @PostMapping(value = "/login.do")
    // public String loginPOST(
    //     HttpServletRequest request, HttpSession session
    // ){
    //     try {
    //         // 세션에 이전 페이지 정보 저장
    //         String referer = request.getHeader("Referer");
    //         session.setAttribute("prevPage", referer);
    //         return "redirect:/home.do";
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //         return "redirect:/home.do";
    //     }
    // }

    // 127.0.0.1:9090/CUSTOM/logout.do
    @GetMapping(value = "/logout.do")
    public String logoutGET(){
        try{
            return "home";
        }
        catch(Exception e){
            e.printStackTrace();
            return "redirect:/home.do";
        }
    } // OK : 5/17
    // 이것도 역시 html에서는 post로 보내고, controller에서는 get만 쓴다.


    // 127.0.0.1:9090/CUSTOM/home/image?ino=1
    @GetMapping(value = "/image")
    public ResponseEntity<byte[]> image(@RequestParam(name = "ino", defaultValue = "0") BigInteger ino) throws IOException{
        TshirtImage obj = tiRepository.findById(ino).orElse(null);
        //log.info("objobj => {}", obj);
        HttpHeaders headers = new HttpHeaders(); // import org.springframework.http.HttpHeaders;
        if(obj != null){ // 이미지가 존재하는지 확인
            if(obj.getIsize() != null){
                headers.setContentType( MediaType.parseMediaType(obj.getItype()));
                return new ResponseEntity<>(obj.getIdata(), headers, HttpStatus.OK);
            }
        }
        // 이미지가 없을 경우
        InputStream is = resourceLoader.getResource("./img/no-image.png").getInputStream(); //?
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<>(is.readAllBytes(), headers, HttpStatus.OK);
    }

    @GetMapping(value="/fqa.do")
    public String fqaGET(
        @AuthenticationPrincipal MemberUser user,
        Model model

    ) {
        try {
            if(user != null){ // 로그인 되었음
                log.info("로그인user => {}", user); 
                //로그인user => MemberUser(username=aaa, authorities=[ROLE_MEMBER], name=aaa)
                }
            model.addAttribute("user", user);

            return "fqa";
        } catch (Exception e) {
            e.printStackTrace();
            return "home";
        }
    }

    // 127.0.0.1:9090/CUSTOM/product.do
    @GetMapping(value = "/product.do")
    public String  productGET(Model model, 
            @ModelAttribute TshirtProductSorting obj, 
            @RequestParam(name="typeCode", defaultValue = "0") int typeCode,
            HttpServletRequest request,
            @AuthenticationPrincipal MemberUser user
            ){
        try{
            // 로그인
            if(user != null){ 
                log.info("로그인user => {}", user); 
                }
            model.addAttribute("user", user);

            // 1. 전부 호출
            List<TshirtView4> list2 = tv4Repository.findAll();
            
            // 2. 타입코드에 따라 구분 
            if(typeCode > 0){ 
                list2 = tv4Repository.findByTtnoOrderByTnoDesc(BigInteger.valueOf(typeCode)); // 티셔츠뷰 중 일부를 호출
            }   

            // 3. 티셔츠 이미지 호출
            if( list2 != null ){ 
                for(TshirtView4 tmp : list2){
                    tmp.setImageUrl(request.getContextPath() + "/image?ino=" + tmp.getIno());
                }
                log.info("리스트 => {}", list2.toString());
            }

            // 4. 값 보냄
            model.addAttribute("search", obj);
            model.addAttribute("list", list2);

            return "product";
        }
        catch(Exception e){
            e.printStackTrace();
            return "redirect:/home.do";
        }
    }

    // 127.0.0.1:9090/CUSTOM/product.do
    @PostMapping(value = "/product.do")
    public String productPOST(){
        try {

            return "redirect:/product.do";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/home.do";
    }

    @GetMapping(value = "/printing.do")
    public String printingGET(Model model, @AuthenticationPrincipal MemberUser user){
        try {
            if(user != null){ // 로그인 되었음
                log.info("로그인user => {}", user); 
                //로그인user => MemberUser(username=aaa, authorities=[ROLE_MEMBER], name=aaa)
                }
                model.addAttribute("user", user);
            return "printing";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/home.do";
        }
    }

    @GetMapping(value = "/403page.do")
    public String page3GET(){
        
        return "/403page";
    }
    
    @GetMapping(value = "/404page.do")
    public String page4GET(){
        
        return "/404page";
    }
}
