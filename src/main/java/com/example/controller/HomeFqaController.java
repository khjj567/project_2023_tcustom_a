package com.example.controller;

import java.math.BigInteger;
import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.dto.MemberUser;
import com.example.entity.HomeAsk;
import com.example.repository.HomeAskRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


    // HomeAnnounce 
    // homeFQA

@Controller
@Slf4j
@RequiredArgsConstructor
// @RequestMapping
public class HomeFqaController {
    
    // homeAsk (게시판고객질문등록 게시판고객질문 마이페이지고객질문목록)
    // homeAskAnswer (게시판에 답변하기 : 관리자권한)
    
    final HomeAskRepository hAskRepository;
    @GetMapping(value = "/fqa.do")
    public String fqapageGET(
        Model model, 
        @AuthenticationPrincipal MemberUser user,
        @RequestParam(name="menu", required = false, defaultValue = "0") int menu
    ){
        try {
            if(user != null){ // 로그인 되었음
                    log.info("로그인user => {}", user); 
                    //로그인user => MemberUser(username=aaa, authorities=[ROLE_MEMBER], name=aaa)
                    }
                model.addAttribute("user", user);

                log.info("고객 =>{}", user);
            if(menu ==1){
                
            }

            if(menu == 2){
                // 고객게시글 목록
                List<HomeAsk> hAsklist = hAskRepository.findAll();
                log.info("리스트 => {}", hAsklist);
                model.addAttribute("hAsklist", hAsklist);

            }

            if(menu == 3){
                
            }
            return "fqa";
            
        } catch (Exception e) {
            e.printStackTrace();
            return "home";
        }
    }

    @GetMapping(value = "/ask.do")
    public String askGET(
        Model model, 
        @AuthenticationPrincipal MemberUser user
    ){
        try {
            if(user != null){ // 로그인 되었음
                    log.info("로그인user => {}", user); 
                    //로그인user => MemberUser(username=aaa, authorities=[ROLE_MEMBER], name=aaa)
                    }
                model.addAttribute("user", user);
                
            return "/fqa/ask";
        } catch (Exception e) {
            e.printStackTrace();
            return "home";
        }
    }
    
    @PostMapping(value = "/askone.do")
    public String askonePOST(
        Model model, 
        @AuthenticationPrincipal MemberUser user,
        @RequestParam(name="hasktitle") String hasktitle,
        @RequestParam(name="content") String haskcontent,
        @RequestParam(name="mid") String mid,
        @RequestParam(name="menu", required = false, defaultValue = "0") int menu
    ){
        try {
            if(user != null){ // 로그인 되었음
                    log.info("로그인user => {}", user); 
                    //로그인user => MemberUser(username=aaa, authorities=[ROLE_MEMBER], name=aaa)
            }
            model.addAttribute("user", user);

            log.info("haskcontent=>{}", haskcontent);
            log.info("hasktitle=>{}", hasktitle);

            HomeAsk hAsk = new HomeAsk();
            hAsk.setHasktitle(hasktitle);
            hAsk.setHaskcontent(haskcontent);
            hAsk.setMid(mid);
            hAsk.setHaskhit(BigInteger.valueOf(1));

            hAskRepository.save(hAsk);

            return "redirect:/fqa.do?menu=2";
        } catch (Exception e) {
            e.printStackTrace();
            return "home";
        }
    }
}
