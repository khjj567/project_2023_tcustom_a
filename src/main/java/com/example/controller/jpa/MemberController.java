package com.example.controller.jpa;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.dto.MemberUser;
import com.example.entity.TshirtDesignView;
import com.example.repository.MemberRepository;
import com.example.repository.TshirtDesignViewRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "/member")
public class MemberController {

    final String format = "고객컨트롤러 => {}";
    final MemberRepository mRepository;
    final TshirtDesignViewRepository tdvRepository;

    @GetMapping(value = "/mypage.do")
    public String mypageGET(
        Model model, 
        @AuthenticationPrincipal MemberUser user,
        @RequestParam(name="menu", required = false, defaultValue = "0") int menu
    ){
        if(menu ==1){
            log.info(format, user.getUsername());
            // 세션에서 아이디정보 꺼내서 mapper에 조회
            List<TshirtDesignView> obj = tdvRepository.findByMidOrderByDregdateDesc(user.getUsername());
            log.info(format, obj);

            // model.addAttribute("obj", obj);
            // log.info(format, obj.toString()); 

            // // 체크박스(연습)에 표시할 항목들
            // String[] checkLabel = {"김효정", "sss", "ddd", "eee", "김효정"};
            // model.addAttribute("checklabel", checkLabel);
        }
        return "/member/mypage";
    }
}
