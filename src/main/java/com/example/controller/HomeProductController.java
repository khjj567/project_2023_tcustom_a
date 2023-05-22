package com.example.controller;

import java.math.BigInteger;

import org.springframework.stereotype.Controller;
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
    public String makingGET(){
        try {
            return "/product/making";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/home.do";
        }
        
    }

    @GetMapping(value = "/insertimage.do")
    public String insertimageGET(@ModelAttribute TshirtImage obj){
        try {
            log.info("이미지정보 => {}", obj.toString());
            // , @RequestParam(name="file") MultipartFile file
            // // 파일은 수동으로 obj에 추가
            // obj.setIname(file.getOriginalFilename());
            // obj.setIsize(BigInteger.valueOf(file.getSize()));
            // // (BigInteger.valueOf(file.getSize())) : long,int -> BigInteger 형변환
            // obj.setItype(file.getContentType());
            // obj.setIdata(file.getInputStream().readAllBytes());
            return "/product/insertimage";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/product.do";
        }
        
    }
    @PostMapping(value="/insertimage.do")
    public String insertimagePOST() {
        try {

            return "redirect:/product/insertimage.do";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/product.do";
        }
    }
    
}
