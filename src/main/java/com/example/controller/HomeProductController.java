package com.example.controller;

import java.math.BigInteger;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.entity.TshirtImage;
import com.example.repository.TshirtImageRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;



@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "/product")
public class HomeProductController {
    
    final TshirtImageRepository tiRepository;

    @GetMapping(value = "/making.do")
    public String makingGET(Model model, @RequestParam(name="tno") long tno){
        try {
            model.addAttribute("tno", tno);
            return "product/making";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/home.do";
        }
        
    }

    @PostMapping(value = "/making.do")
    public String makingPOST(){
        try {
            // 파일정보
            // 사이즈
            // 컬러(콤보박스)
            // 프린팅방식 
            // 수량
            return "redirect:product/making.do";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/home.do";
        }
    }

    @GetMapping(value = "/insertimage.do")
    public String insertimageGET(Model model, @RequestParam(name="tno") long tno){
        try {
            model.addAttribute("tno", tno);
            return "product/insertimage";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/product.do";
        }
        
    }
    @PostMapping(value="/insertimage.do")
    public String insertimagePOST(@ModelAttribute TshirtImage obj
                        , @RequestParam(name="file") MultipartFile file
                        ) {
        try {
            //log.info("이미지정보 => {}", obj.getTno());
            // 파일은 수동으로 obj에 추가
            obj.setIname(file.getOriginalFilename());
            obj.setIsize(BigInteger.valueOf(file.getSize()));
            // (BigInteger.valueOf(file.getSize())) : long,int -> BigInteger 형변환
            obj.setItype(file.getContentType());
            obj.setIdata(file.getInputStream().readAllBytes());
            
            tiRepository.save(obj);
            // 왜 안되지

            return "redirect:/product.do?tno=" + obj.getTshirt().getTno();
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/home.do";
        }
    }
    
}
