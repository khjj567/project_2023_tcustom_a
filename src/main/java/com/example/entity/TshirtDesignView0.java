package com.example.entity;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.Immutable;

import lombok.Data;

@Entity
@Immutable
@Data
@Table(name = "TSHIRTDESIGNVIEW0")
public class TshirtDesignView0 {
    
    @Id
    @Column(name = "PNO")
    private	BigInteger	pno	; // 프린팅 no

    private	BigInteger	pprice	; // 프린팅가격
    private	String	pmethod	; // 프린팅방식

    private	BigInteger	tno	; // 티셔츠no
    private	String	tname	; // 티셔츠 이름
    private	BigInteger	tprice	; // 티셔츠 가격
    private	BigInteger	tquantity	; // 티셔츠 수량

    private BigInteger tsno ;  // 티셔츠 no
    private	String	tssize	; // 티셔츠 사이즈

    private BigInteger tcolorno ; // 티셔츠 컬러 no
    private	String	tcolorname	; // 티셔츠 컬러 이름

}
