package com.example.controller;

import java.math.BigInteger;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.dto.MemberUser;
import com.example.entity.HomeAnnounce;
import com.example.entity.HomeAsk;
import com.example.entity.HomeFqa;
import com.example.repository.HomeAnnounceRepository;
import com.example.repository.HomeAskRepository;
import com.example.repository.HomeFqaRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "/fqa")
public class HomeFqaController {
    
    final HomeAskRepository hAskRepository;
    final HomeAnnounceRepository hAnRepository;
    final HomeFqaRepository hFqaRepository;

    // homeAsk (게시판고객질문등록 게시판고객질문 마이페이지고객질문목록)
    // homeAskAnswer (게시판에 답변하기 : 관리자권한)
    
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
        @RequestParam(name="content") String haskcontent
        // @RequestParam(name="mid") String mid
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
            hAsk.setMid(user.getUsername());
            hAsk.setHaskhit(BigInteger.valueOf(1));

            hAskRepository.save(hAsk);

            return "redirect:/fqa.do?menu=2";
        } catch (Exception e) {
            e.printStackTrace();
            return "home";
        }
    }

    @GetMapping(value="/ask1.do")
    public String ask1GET(
        Model model, 
        @AuthenticationPrincipal MemberUser user,
        @RequestParam(name= "haskno") BigInteger haskno
    ) {
        try {
            if(user != null){ // 로그인 되었음
                log.info("로그인user => {}", user); 
                //로그인user => MemberUser(username=aaa, authorities=[ROLE_MEMBER], name=aaa)
            }
            model.addAttribute("user", user);

            // 보여줄내용던짐
            HomeAsk obj1 = hAskRepository.findByHaskno(haskno);
            log.info("고객게시글번호! => {}", obj1);
            model.addAttribute("obj1", obj1);

            // 조회수 void
            hAskRepository.updateHaskhitCount(haskno);
            
            return "fqa/ask1";
        } catch (Exception e) {
            e.printStackTrace();
            return "home";
        }
    }

    @GetMapping(value = "/ask1update.do")
    public String ask1updateGET(
        Model model, 
        @AuthenticationPrincipal MemberUser user,
        @RequestParam(name= "haskno") BigInteger haskno
    ) {
        try {
            if(user != null){ // 로그인 되었음
                log.info("로그인user => {}", user); 
                //로그인user => MemberUser(username=aaa, authorities=[ROLE_MEMBER], name=aaa)
            }
            model.addAttribute("user", user);
            
            HomeAsk obj = hAskRepository.findByHaskno(haskno);
            model.addAttribute("obj", obj);

            return "fqa/ask1update";
        }
        catch(Exception e) {
            e.printStackTrace();
            return "home";
        }
    }
    
    @PostMapping(value="/ask1update.do")
    public String ask1updatePOST(
        Model model, 
        @AuthenticationPrincipal MemberUser user,
        @RequestParam(name= "haskno") BigInteger haskno,
        @RequestParam(name= "haskcontent") String haskcontent,
        @RequestParam(name= "hasktitle") String hasktitle
    ) {
        try {
            if(user != null){ // 로그인 되었음
                log.info("로그인user => {}", user); 
                //로그인user => MemberUser(username=aaa, authorities=[ROLE_MEMBER], name=aaa)
            }
            model.addAttribute("user", user);
            
            HomeAsk hAsk = hAskRepository.findByHaskno(haskno);
            hAsk.setHaskcontent(haskcontent);
            hAsk.setHasktitle(hasktitle);
            hAskRepository.save(hAsk);

            return "redirect:/fqa/ask1.do?haskno=" + haskno;
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/home.do";
        }
    }

    @GetMapping(value="/announce.do")
    public String announceGET(Model model, 
    @AuthenticationPrincipal MemberUser user) {
        try {
            if(user != null){ // 로그인 되었음
                log.info("로그인user => {}", user); 
                //로그인user => MemberUser(username=aaa, authorities=[ROLE_MEMBER], name=aaa)
                }
            model.addAttribute("user", user);

            return "/fqa/announce";
            
        } catch (Exception e) {
            e.printStackTrace();
            return "home";
        }
    }
    
    @PostMapping(value="/announceone.do")
    public String announceonePOST(
        Model model, 
        @AuthenticationPrincipal MemberUser user,
        @RequestParam(name="hantitle") String hantitle,
        @RequestParam(name="hancontent") String hancontent,
        @RequestParam(name="mid") String mid
    ) {
        try {
            if(user != null){ // 로그인 되었음
                log.info("로그인user => {}", user); 
                //로그인user => MemberUser(username=aaa, authorities=[ROLE_MEMBER], name=aaa)
            }
            model.addAttribute("user", user);
            
            log.info("hancontent => {}", hancontent);
            log.info("hantitle => {}", hantitle);

            HomeAnnounce hAnnounce = new HomeAnnounce();
            hAnnounce.setHantitle(hantitle);
            hAnnounce.setHancontent(hancontent);
            hAnnounce.setHanhit(BigInteger.valueOf(1));
            hAnRepository.save(hAnnounce);

            return "redirect:/fqa.do?menu=1";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/home.do";
        }
    }

    @GetMapping(value="/fqaup.do")
    public String fqaupGET(
        Model model, 
        @AuthenticationPrincipal MemberUser user
    ) {
        try {
            if(user != null){ // 로그인 되었음
                log.info("로그인user => {}", user); 
                //로그인user => MemberUser(username=aaa, authorities=[ROLE_MEMBER], name=aaa)
            }
            model.addAttribute("user", user);

            return "/fqa/fqaup";
        } catch (Exception e) {
            e.printStackTrace();
            return "home";
        }
    }

    @PostMapping(value = "/fqaone.do")
    public String fqaonePOST(
        Model model, 
        @AuthenticationPrincipal MemberUser user,
        @RequestParam(name="hquestion") String hquestion,
        @RequestParam(name="hanswer") String hanswer,
        @RequestParam(name="mid") String mid
    ){
        try {
            if(user != null){ // 로그인 되었음
                log.info("로그인user => {}", user); 
                //로그인user => MemberUser(username=aaa, authorities=[ROLE_MEMBER], name=aaa)
            }
            model.addAttribute("user", user);
            
            log.info("hquestion => {}", hquestion);
            log.info("hanswer => {}", hanswer);

            HomeFqa hFqa = new HomeFqa();
            hFqa.setHquestion(hquestion);
            hFqa.setHanswer(hanswer);
            
            hFqaRepository.save(hFqa);

            return "redirect:/fqa.do?menu=3";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/home.do";
        }
    }
    
    @GetMapping(value="/announce1.do")
    public String announce1GET(
        Model model, 
        @AuthenticationPrincipal MemberUser user,
        @RequestParam(name= "hanno") BigInteger hanno
    ) {
        try {
            if(user != null){ // 로그인 되었음
                log.info("로그인user => {}", user); 
                //로그인user => MemberUser(username=aaa, authorities=[ROLE_MEMBER], name=aaa)
            }
            model.addAttribute("user", user);

            // 보여줄내용던짐
            HomeAnnounce obj = hAnRepository.findByHanno(hanno);
            model.addAttribute("obj", obj);
            log.info("고객문의 => {}", obj);

            // 조회수 void
            hAnRepository.updateHanhitCount(hanno);
            
            return "fqa/announce1";
        } catch (Exception e) {
            e.printStackTrace();
            return "home";
        }
    }

    @GetMapping(value = "/announce1update.do")
    public String announce1updateGET(
        Model model, 
        @AuthenticationPrincipal MemberUser user,
        @RequestParam(name= "hanno") BigInteger hanno
    ) {
        try {
            if(user != null){ // 로그인 되었음
                log.info("로그인user => {}", user); 
                //로그인user => MemberUser(username=aaa, authorities=[ROLE_MEMBER], name=aaa)
            }
            model.addAttribute("user", user);
            HomeAnnounce obj = hAnRepository.findByHanno(hanno);
            model.addAttribute("obj", obj);

            return "fqa/announce1update";
        }
        catch(Exception e) {
            e.printStackTrace();
            return "home";
        }
    }

    @PostMapping(value="/announce1update.do")
    public String announce1updatePOST(
        Model model, 
        @AuthenticationPrincipal MemberUser user,
        @RequestParam(name= "hanno") BigInteger hanno,
        @RequestParam(name= "hancontent") String hancontent,
        @RequestParam(name= "hantitle") String hantitle
    ) {
        try {
            if(user != null){ // 로그인 되었음
                log.info("로그인user => {}", user); 
                //로그인user => MemberUser(username=aaa, authorities=[ROLE_MEMBER], name=aaa)
            }
            model.addAttribute("user", user);
            
            HomeAnnounce hAnnounce = hAnRepository.findByHanno(hanno);
            hAnnounce.setHancontent(hancontent);
            hAnnounce.setHantitle(hantitle);
            hAnRepository.save(hAnnounce);

            return "redirect:/fqa/announce1.do?hanno=" + hanno;
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/home.do";
        }
    }
}
