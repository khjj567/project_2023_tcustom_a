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

@Data
@Entity
@Immutable
@Table(name="TSHIRTDESIGNVIEW1")
public class TshirtDesignView1 {

    @Id
    @Column(name="DNO")
    private	BigInteger	dno	; // 디자인 번호
    private	String	mid	; // 회원아이디
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    @CreationTimestamp
    private	Date	dregdate	; // 디자인등록날짜

    private	BigInteger	pno	; //프린팅번호
    private	BigInteger	pprice	; //프린팅가격
    private	String	pmethod	; // 프린팅방식

    private	BigInteger	fno	; // 파일번호
    private	String	fname	; // 파일이름
    private	BigInteger	fsize	; // 파일사이즈
    @Lob
    @ToString.Exclude
    private	byte[]	fdata	; // 파일데이타
    private	String	ftype	; // 파일타입
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    @CreationTimestamp
    private	Date	fregdate	; // 파일등록날짜

    private	BigInteger	psno	; // 프린팅사이드 번호
    private	String	psidename	; // 프린팅사이드 이름

    private	BigInteger	tno	; // 티셔츠번호
    private	String	tname	; // 티셔츠이름
    private	BigInteger	tprice	; // 티셔츠가격
    private	BigInteger	tquantity	; // 티셔츠수량(나중에 품절나면 띄워야함)
    
    private	BigInteger	ino	; // 티셔츠이미지 번호
    private	String	iname	; // 티셔츠이미지 이름
    private	BigInteger	isize	; // 티셔츠이미지 사이즈
    @Lob
    @ToString.Exclude
    private	byte[]	idata	; // 티셔츠이미지 데이타
    private	String	itype	; // 티셔츠이미지 타입

    private	BigInteger	tsno	; // 티셔츠사이즈번호
    private	String	tssize	; //티셔츠사이즈

    private	BigInteger	tcolorno	; // 티셔츠컬러번호
    private	String	tcolorname	; // 티셔츠컬러이름
    
}
