package com.example.controller.jpa;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.dto.MemberUser;
import com.example.entity.DesignOne;
import com.example.entity.File;
import com.example.entity.MemberFileView;
import com.example.entity.Orders;
import com.example.entity.Printing;
import com.example.entity.PrintingSide;
import com.example.entity.Tshirt;
import com.example.entity.TshirtColor;
import com.example.entity.TshirtSize;
import com.example.repository.DesignOneRepository;
import com.example.repository.FileRepository;
import com.example.repository.MemberFileViewRepository;
import com.example.repository.MemberRepository;
import com.example.repository.OrdersRepository;
import com.example.repository.PrintingRepository;
import com.example.repository.PrintingSideRepository;
import com.example.repository.TsDesignViewRepository;
import com.example.repository.TsOrdersDesignViewRepository;
import com.example.repository.TshirtColorRepository;
import com.example.repository.TshirtDesignViewRepository;
import com.example.repository.TshirtRepository;
import com.example.repository.TshirtSizeRepository;

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
    final TsDesignViewRepository tsdvRepository;
    final DesignOneRepository dOneRepository;

    final MemberFileViewRepository mfvRepository;
    final TsOrdersDesignViewRepository tsOdvRepository;
    final OrdersRepository ordersRepository;
    final TshirtColorRepository tcRepository;
    final TshirtSizeRepository tsRepository;
    final TshirtRepository tRepository;
    final PrintingRepository pRepository;
    final PrintingSideRepository psRepository;
    final FileRepository fRepository;

    final ResourceLoader resourceLoader;


    @GetMapping(value = "/mypage.do")
    public String mypageGET(
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

            if(menu ==1){
                // log.info(format, user.getUsername());
                // 세션에서 아이디정보 꺼내서 mapper에 조회
                List<DesignOne> obj = dOneRepository.findByMember_MidOrderByDnoDesc(user.getUsername());
                // log.info("TshirtDesignView => {}", obj);
                model.addAttribute("obj", obj);
            }

            if(menu == 2){
                List<MemberFileView> obj2 = mfvRepository.findByMidOrderByDnoDesc(user.getUsername());
                log.info("TshirtDesignView => {}", obj2);
                model.addAttribute("obj2", obj2);
            }

            if(menu == 3){
                List<Orders> obj3 = ordersRepository.findByDesignOne_Member_MidOrderByOno(user.getUsername());
                for(Orders orders : obj3){
                    TshirtColor tcolor = tcRepository.findByTcolorno(orders.getDesignOne().getTcolorno());
                    orders.setTcolorname(tcolor.getTcolorname());

                    TshirtSize tSize = tsRepository.findByTsno(orders.getDesignOne().getTsno());
                    orders.setTssize(tSize.getTssize());

                    Tshirt tshirt = tRepository.findByTno(orders.getDesignOne().getTshirt().getTno());
                    orders.setTname(tshirt.getTname());
                    orders.setTprice(tshirt.getTprice());

                    Printing printing = pRepository.findBypno(orders.getDesignOne().getPrinting().getPno());
                    orders.setPmethod(printing.getPmethod());
                    orders.setPprice(printing.getPprice());

                    PrintingSide pside = psRepository.findByPsno(orders.getDesignOne().getPrintingSide().getPsno());
                    orders.setPsidename(pside.getPsidename());
                    
                    File file = fRepository.findByFno(orders.getDesignOne().getFile().getFno());
                    orders.setFno(file.getFno());
                    // log.info("주문목록 => {}", orders);
                }
                log.info("주문목록 => {}", obj3);
                
                model.addAttribute("obj3", obj3);
            }
            return "/member/mypage";
        } catch (Exception e) {
            e.printStackTrace();
            return "home";
        }
    }

    @PostMapping(value = "/mypage")
    public String mypagePOST(
        Model model, 
        @RequestParam(name="menu", required = false, defaultValue = "0") int menu
    ){
        try {
            if(menu == 3){

            }
            return "redirect:/mypage.do";
        } catch (Exception e) {
            e.printStackTrace();
            return "home";
        }
    }

    @GetMapping(value = "/image")
    public ResponseEntity<byte[]> image(@RequestParam(name = "fno", defaultValue = "0") BigInteger fno) throws IOException{
        File obj = fRepository.findById(fno).orElse(null);
        //log.info("objobj => {}", obj);
        HttpHeaders headers = new HttpHeaders(); // import org.springframework.http.HttpHeaders;
        if(obj != null){ // 이미지가 존재하는지 확인
            if(obj.getFsize() != null){
                headers.setContentType( MediaType.parseMediaType(obj.getFtype()));
                return new ResponseEntity<>(obj.getFdata(), headers, HttpStatus.OK);
            }
        }
        // 이미지가 없을 경우
        InputStream is = resourceLoader.getResource("./img/no-image.png").getInputStream(); //?
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<>(is.readAllBytes(), headers, HttpStatus.OK);
    }
    
    @GetMapping(value = "/image.do")
    public String imageGET(
        @RequestParam(name="fno") long fno, 
        Model model, 
        HttpServletRequest request,
        @AuthenticationPrincipal MemberUser user
        ){
        try {
            if(user != null){ // 로그인 되었음
                log.info("로그인user => {}", user); 
                //로그인user => MemberUser(username=aaa, authorities=[ROLE_MEMBER], name=aaa)
                }
            model.addAttribute("user", user);

      

            return "/member/image";
        } catch (Exception e) {
            e.printStackTrace();
            return "home";
        }
    }

}
