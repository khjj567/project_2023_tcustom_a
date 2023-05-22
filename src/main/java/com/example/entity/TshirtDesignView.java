package com.example.entity;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.Immutable;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.ToString;

@Entity
@Immutable
@Data
@Table(name = "TSHIRTDESIGNVIEW")
public class TshirtDesignView {
    

    @Id
    @Column(name = "DNO")
    private	BigInteger	dno	; // 디자인no

    private	String	mid	; // 고객아이디

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    @CreationTimestamp 
    private Date dregdate	; // 디자인생성일

    private	BigInteger	pprice	; // 프린팅가격
    private	String	pmethod	; // 프린팅방식

    private	String	fname	; // 파일명
    private	BigInteger	fsize	; // 파일사이즈
    
    @Lob
    @ToString.Exclude 
    private byte[] 	fdata	; // 파일데이터

    private	String	ftype	; // 파일타입

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    @CreationTimestamp 
    private Date fregdate	; // 파일등록일

    private	String	psidename	; // 프린팅사이드 이름

    private	String	dpname	; // 디자인픽 이름
    private	BigInteger	dpsize	; // 디자인픽 사이즈

    @Lob 
    @ToString.Exclude 
    private byte[] 	dpdata	; // 디자인픽 데이타

    private	String	dptype	; // 디자인픽 타입

    private	BigInteger	tno	; // 티셔츠no
    private	String	tname	; // 티셔츠 이름
    private	BigInteger	tprice	; // 티셔츠 가격
    private	BigInteger	tquantity	; // 티셔츠 수량

    private	String	ttname	; // 티셔츠 타입이름

    private	String	iname	; // 티셔츠이미지 이름
    private	BigInteger	isize	; // 티셔츠 이미지 사이즈

    @Lob
    @ToString.Exclude 
    private byte[] 	idata	; // 티셔츠 이미지 데이타

    private	String	itype	; // 티셔츠 이미지 타입

    private	String	tssize	; // 티셔츠 사이즈

    private	String	tcolorname	; // 티셔츠 컬러 이름
    
    private	BigInteger	totalprice	; // 총금액

    



}
