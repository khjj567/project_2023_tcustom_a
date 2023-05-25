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

import com.example.dto.PrintingDTO;
import com.example.dto.PrintingSideDTO;
import com.example.dto.TshirtColorDTO;
import com.example.dto.TshirtSizeDTO;
import com.example.entity.Printing;
import com.example.entity.PrintingSide;
import com.example.entity.TshirtColor;
import com.example.entity.TshirtImage;
import com.example.entity.TshirtSize;
import com.example.repository.FileRepository;
import com.example.repository.PrintingRepository;
import com.example.repository.PrintingSideRepository;
import com.example.repository.TshirtColorRepository;
import com.example.repository.TshirtImageRepository;
import com.example.repository.TshirtSizeRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;



@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "/product")
public class HomeProductController {
    
    final TshirtImageRepository tiRepository;
    final TshirtSizeRepository tsRepository;
    final PrintingRepository pRepository;
    final TshirtColorRepository tcRepository;
    final FileRepository fRepository;
    final PrintingSideRepository psRepository;

    //127.0.0.1:9090/CUSTOM/product/making.do
    @GetMapping(value = "/making.do")
    public String makingGET(
            Model model, 
            @RequestParam(name="tno") long tno){
        try {
            // 수량  // post에서 보내야함
            
            // 파일정보 // post에서 보내야함

            // 프린팅사이드 // 라디오버튼..
            PrintingSideDTO psdto = new PrintingSideDTO();
            List<PrintingSide> psList = psRepository.findAll();
            psdto.setList(psList);

            // 컬러(콤보박스)
            TshirtColorDTO tcdto = new TshirtColorDTO();
            List<TshirtColor> tclist = tcRepository.findByTshirt_tno(BigInteger.valueOf(tno));
            tcdto.setList(tclist);
            //log.info("컬러정보 => {}", tclist.toString());

            // 프린팅방식 printing 테이블의 pmethod를 가져옴
            PrintingDTO pdto = new PrintingDTO();
            List<Printing> plist = pRepository.findAll();
            pdto.setList(plist);

            // 사이즈
            TshirtSizeDTO tsdto = new TshirtSizeDTO(); // select에서 사용해야하는 selectsize변수와 List<TshirtSize> list가 들어가 있는 TshirtSizeDTO
            List<TshirtSize> list = tsRepository.findByTshirt_tno(BigInteger.valueOf(tno));
            tsdto.setList(list); // 위에서 생성한 list를 tsdto의 list에 set해 준다
            log.info("티셔츠사이즈정보 => {}", list.toString()); // 모든 정보가 나옴

            model.addAttribute("tno", tno);

            model.addAttribute("psdto", psdto);
            // model.addAttribute("tclist", tclist);

            model.addAttribute("tcdto", tcdto);
            model.addAttribute("tclist", tclist);

            model.addAttribute("pdto", pdto);
            model.addAttribute("plist", plist);

            model.addAttribute("tsdto", tsdto);
            model.addAttribute("obj", list);

            return "product/making";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/home.do";
        }
    }

    // 127.0.0.1:9090/CUSTOM/product/making.do
    @PostMapping(value = "/making.do")
    public String makingPOST(){
        try {

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
