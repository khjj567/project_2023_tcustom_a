package com.example.controller;

import java.math.BigInteger;
import java.util.List;

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
import com.example.entity.TshirtView1;
import com.example.repository.MemberRepository;
import com.example.repository.TshirtView1Repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j

@RequiredArgsConstructor
public class HomeController {

    final MemberRepository mRepository;
    final TshirtView1Repository tv1Repository;
    BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();

    //127.0.0.1:9090/CUSTOM/home.do
    @GetMapping(value = "/home.do")  
    public String homeGET(Model model, @AuthenticationPrincipal MemberUser user){
        if(user != null){ // 로그인 되었음
        log.info("로그인user => {}", user); 
        //로그인user => MemberUser(username=aaa, authorities=[ROLE_MEMBER], name=aaa)
        }
        model.addAttribute("user", user);
        // user에서 값을 꺼낼때는 MemberUser에서 임의로 지정한 값을 사용하여야 한다.
        // user.mid (x)  ->  user.id
        // user.mname (x)  ->  user.name
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
            return "redirect:/join.do";
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

        // 127.0.0.1:9090/CUSTOM/product.do
        @GetMapping(value = "/product.do")
        public String  productGET(Model model, 
                @ModelAttribute TshirtProductSorting obj, 
                @RequestParam(name="typeCode", defaultValue = "0") int typeCode){
            try{

                List<TshirtView1> list = null;
                if(typeCode == 0){
                    list = tv1Repository.findAll();
                }
                else {
                    list = tv1Repository.findByTtnoOrderByTnoDesc(BigInteger.valueOf(typeCode));
                }

                //log.info("리스트 =>{}", list.toString());
                model.addAttribute("search", obj);
                model.addAttribute("list", list);
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
        public String printingGET(){
            try {
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
