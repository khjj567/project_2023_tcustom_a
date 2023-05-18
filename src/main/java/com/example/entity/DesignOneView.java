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
@Immutable // VIEW entity 만들땐 필수 어노테이션
@Entity
@Table(name = "DESIGNONEVIEW")
public class DesignOneView {
    
    @Id
    @Column(name = "DNO")
    private	BigInteger	dno	; // 디자인 no

    private	String	mid	; // 고객 아이디

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    @CreationTimestamp 
    private Date dregdate	; // 디자인 생성날짜

    private	BigInteger	tno	; // 티셔츠no
    private	BigInteger	pno	; // 프린팅no
    private	BigInteger	pprice	; // 프린팅 가격
    private	String	pmethod	; // 프린팅방식

    private	BigInteger	fno	; // 파일no
    private	String	fname	; // 파일 이름
    private	BigInteger	fsize	; //파일 사이즈

    @Lob 
    @ToString.Exclude 
    private byte[] 	fdata	; // 파일데이타

    private	String	ftype	; // 파일타입

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    @CreationTimestamp
    private Date fregdate	; // 파일등록성일자

    private	BigInteger	psno	; // 프린팅사이드no
    private	String	psidename	; // 프린팅사이드 이름

    private	BigInteger	dpno	; // 디자인픽no
    private	String	dpname	; // 디자인픽 이름

    @Lob 
    @ToString.Exclude 
    private byte[] 	dpdata	; // 디자인픽 생성일자

    private	String	dptype	; // 디자인픽 타입
    

}
