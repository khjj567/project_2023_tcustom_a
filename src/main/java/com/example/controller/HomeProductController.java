package com.example.controller;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.dto.MemberUser;
import com.example.dto.PrintingDTO;
import com.example.dto.PrintingSideDTO;
import com.example.dto.PsidePicSorting;
import com.example.dto.TshirtColorDTO;
import com.example.dto.TshirtPrintingSidePicViewDTO;
import com.example.dto.TshirtSizeDTO;
import com.example.entity.File;
import com.example.entity.Printing;
import com.example.entity.PrintingSide;
import com.example.entity.PsidePic;
import com.example.entity.Tshirt;
import com.example.entity.TshirtColor;
import com.example.entity.TshirtContentView;
import com.example.entity.TshirtImage;
import com.example.entity.TshirtPrintingSidePicView;
import com.example.entity.TshirtSize;
import com.example.repository.FileRepository;
import com.example.repository.PrintingRepository;
import com.example.repository.PrintingSideRepository;
import com.example.repository.PsidePicRepository;
import com.example.repository.TshirtColorRepository;
import com.example.repository.TshirtContentViewRepository;
import com.example.repository.TshirtImageRepository;
import com.example.repository.TshirtPrintingSidePicViewRepository;
import com.example.repository.TshirtSizeRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "/product")
public class HomeProductController {
    
    final TshirtImageRepository tiRepository;
    final PsidePicRepository ppRepository;
    final TshirtSizeRepository tsRepository;
    final PrintingRepository pRepository;
    final TshirtColorRepository tcRepository;
    final FileRepository fRepository;
    final PrintingSideRepository psRepository;
    final TshirtPrintingSidePicViewRepository tpspvRepository;
    final TshirtContentViewRepository tcvRepository;
    final ResourceLoader resourceLoader;


    // 127.0.0.1:9090/CUSTOM/product/image?ino=1
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

    @GetMapping(value = "/psidepic")
    public ResponseEntity<byte[]> psidepic(@RequestParam(name = "pspicno", defaultValue = "0") BigInteger pspicno) throws IOException{
        PsidePic obj = ppRepository.findById(pspicno).orElse(null);
        //log.info("objobj => {}", obj);
        HttpHeaders headers = new HttpHeaders(); // import org.springframework.http.HttpHeaders;
        if(obj != null){ // 이미지가 존재하는지 확인
            if(obj.getPspicsize() != null){
                headers.setContentType( MediaType.parseMediaType(obj.getPspictype()));
                return new ResponseEntity<>(obj.getPspicdata(), headers, HttpStatus.OK);
            }
        }
        // 이미지가 없을 경우
        InputStream is = resourceLoader.getResource("./img/no-image.png").getInputStream(); //?
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<>(is.readAllBytes(), headers, HttpStatus.OK);
        
    }

    //127.0.0.1:9090/CUSTOM/product/making.do
    @GetMapping(value = "/making.do")
    public String makingGET(
            Model model, 
            @RequestParam(name="tno") long tno,
            @RequestParam(name="psno") long psno,
            HttpServletRequest request,
            @AuthenticationPrincipal MemberUser user,
            @ModelAttribute TshirtPrintingSidePicView tpspicView,
            @ModelAttribute PsidePicSorting obj,
            @RequestParam(name="typeCode", defaultValue = "0") int typeCode ){
        try {
            if(user != null){ // 로그인 되었음
                log.info("로그인user => {}", user); 
                //로그인user => MemberUser(username=aaa, authorities=[ROLE_MEMBER], name=aaa)
                }
            model.addAttribute("user", user);



            // 앞면뒷면에 따라 앞면뒷면 사진가져오기
            
            // 1. 앞면 뒷면 나오게 하기
            TshirtPrintingSidePicViewDTO psdto2 = new TshirtPrintingSidePicViewDTO();
            List<TshirtPrintingSidePicView> pspicList = tpspvRepository.findByTno(BigInteger.valueOf(tno));
            
            psdto2.setList(pspicList);
            model.addAttribute("psdto2", psdto2);

            // 2. 앞면뒷면(psno)에 따라 사진나오게 하기
            BigInteger psno1 = tpspicView.getPsno();
            TshirtPrintingSidePicViewDTO psdto1 = new TshirtPrintingSidePicViewDTO();
            // psno와 tno가 일치하는 것을 찾는다
            List<TshirtPrintingSidePicView> pspic = 
                tpspvRepository.findByPsnoAndTno(psno1, BigInteger.valueOf(tno)); // 티셔츠뷰 중 일부를 호출
            // imageUrl 호출
            if( pspic != null ){ 
                for(TshirtPrintingSidePicView tmp : pspic){
                    tmp.setImageUrl(request.getContextPath() + "/product/psidepic?pspicno=" + tmp.getPspicno());
                }
            }

            psdto1.setList(pspic);
            model.addAttribute("psdto1", psdto1);
            log.info("tno로 가져온 뷰 정보 => {}", psdto1.toString());

            model.addAttribute("search", obj);

            // 앞면도 뒷면도 없을때 (처음 화면에 들어갔을때)
            if(psno > 0){
                model.addAttribute("psno", psno);
            }
            else{
                model.addAttribute("psno", 1);
            }


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
            //log.info("티셔츠사이즈정보 => {}", list.toString()); // 모든 정보가 나옴

            // 티셔츠 INFO
            TshirtContentView tcvobj = tcvRepository.findByTno(BigInteger.valueOf(tno));

            // 티셔츠 전체이미지 가져오기
            List<String> imageList = new ArrayList<>();
            List<TshirtImage> list1 = tiRepository.findByTshirt_tnoOrderByInoAsc(BigInteger.valueOf(tno));
            // log.info("what=> {}", list1.toString());
            if( !list1.isEmpty() ){ // 리스트 비어있지 않은ㅇ지 확인
                for(TshirtImage tmp : list1){
                    //127.0.0.1:9090/CUSTOM/product/image?ino=1
                    imageList.add( request.getContextPath() + "/product/image?ino=" + tmp.getIno() );
                }
            }
            // log.info("이미지리스트 => {}", imageList); (성공)

            
            model.addAttribute("tno", tno);

            model.addAttribute("imageList", imageList);

            model.addAttribute("tcdto", tcdto);
            model.addAttribute("tclist", tclist);

            model.addAttribute("pdto", pdto);
            model.addAttribute("plist", plist);

            model.addAttribute("tsdto", tsdto);
            model.addAttribute("obj", list);

            model.addAttribute("tcvobj", tcvobj);
            return "product/making";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/home.do";
        }
    }

    // 127.0.0.1:9090/CUSTOM/product/making.do
    @PostMapping(value = "/making.do")
    public String makingPOST(
            @ModelAttribute File obj,
            
            @RequestParam(name = "tmpFile") MultipartFile file){
        try {

            // 파일 저장 (파일 선택하고 업로드)

            // 디자인 저장
            
            // 파일정보 // post에서 보내야함

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
    @GetMapping(value = "/insertpsidepic.do")
    public String insertpsidepiGET(Model model, 
                    @RequestParam(name="tno") long tno
                    
                    ){
        try {
            
            //log.info("티엔오 => {}", tno);
            
            // 프린팅사이드에 대한 드롭다운 정보제공+
            PrintingSideDTO psdto = new PrintingSideDTO();
            List<PrintingSide> psList = psRepository.findAll();
            psdto.setList(psList);

            // model.addAttribute("obj", obj);
            model.addAttribute("psdto", psdto);
            model.addAttribute("tno", tno);
            
            //model.addAttribute("psno", psno);
            return "product/insertpsidepic";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/product.do";
        } 
    }

    @PostMapping(value = "/insertpsidepic.do")
    public String insertpsidepicPOST(@ModelAttribute PsidePic obj2, 
                @RequestParam(name="file2") MultipartFile file2,
                @RequestParam(name="tno") long tno,
                // @RequestParam(name="psidename") String psidename,
                @RequestParam(name="selectpside", defaultValue = "", required = false) String selectpside
                // 주소에 정보가 없어도 ㄱㄴ 
                // 근데 selectpside를 받으면 psno는 어디서 받아오지
                ){
        try {
            // psidename으로 psno를 찾아
            //log.info("프린팅사이드 => {}", selectpside.toString());
            //log.info("수ㅐ => {}", tno);
            //log.info("파일 => {}", file2.getOriginalFilename());
            // '앞면'을 이용해서 psno를 받아오기
            
            PrintingSide printingSide = psRepository.findByPsidename(selectpside);

            //log.info("프린팅사이드no => {}", printingSide.getPsno());
                
            // 파일받기
            obj2.setPspicname(file2.getOriginalFilename());
            obj2.setPspicsize(BigInteger.valueOf(file2.getSize()));
            obj2.setPspictype(file2.getContentType());
            obj2.setPspicdata(file2.getInputStream().readAllBytes());

            // obj2에는 PrintingSide가 있으니 PrintingSide를 넣어줘야한다
            // PrintingSide에 값을 넣어주려면 그 객체가 필요하다.
            obj2.setPrintingSide(printingSide);

            Tshirt tshirt = new Tshirt();
            // set넣을값 에서 컨트롤스페이스를 눌러 넣어야 할 타입을 찾은 뒤 없으면 새로 객체를 생성
            // 그리고 그 객체에 set을 통해 원하는 값을 넣어준 후 최종적으로 넣어 줘야하는 객체에 값을 넣어줘야 한다.
            tshirt.setTno(BigInteger.valueOf(tno));
            obj2.setTshirt(tshirt);
            log.info("프린팅사이드픽 정보 -> {}", obj2.toString());

            // 오류코드 : Failed to perform cleanup of multipart items :왜?
            // 톰캣을 사용해서 생기는 Caused by: java.io.IOException 오류이다
            //  서버에 올리면 사라짐

            ppRepository.save(obj2);
            
            // /selectlist.do?id=" + id +"&page=1";
            return "redirect:/product.do?tno=" + obj2.getTshirt().getTno() + "&psno=" + printingSide.getPsno();
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/home.do";
        }
    }
    @PostMapping(value="/insertimage.do")
    public String insertimagePOST(@ModelAttribute TshirtImage obj, 
                                @RequestParam(name="file") MultipartFile file
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
