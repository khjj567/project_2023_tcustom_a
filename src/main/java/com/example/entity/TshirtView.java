package com.example.entity;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.springframework.data.annotation.Immutable;

import lombok.Data;
import lombok.ToString;

@Data
@Immutable // VIEW entity 만들땐 필수 어노테이션
@Entity
@Table(name = "TSHIRTVIEW")
public class TshirtView {
    
    @Id
    @Column(name = "TNO")
    private	BigInteger	tno	;
    private	String	tname	;
    private	BigInteger	tprice	;
    private	BigInteger	tquantity	;


    private	BigInteger	ttno	;
    private	String	ttname	;

    private	BigInteger	tcno	;
    @Lob
    private	String	tcinfo	;
    @Lob
    private	String	tcmaterial	;
    @Lob
    private	String	tcmanufacomp	;
    @Lob
    private	String	tcmanufacunt	;
    @Lob
    private	String	tcsize	;
    @Lob
    private	String	tcfit	;
    @Lob
    private	String	tcflexi	;
    @Lob
    private	String	tctexture	;
    @Lob
    private	String	tcthick	;
    @Lob
    private	String	tccaution	;
    @Lob
    private	String	tcwash	;

    private	BigInteger	ino	;
    private	String	iname	;
    private	BigInteger	isize	;
    @Lob 
    @ToString.Exclude 
    private	byte[]	idata	;
    private	String	itype	;

    private	BigInteger	tsno	;
    private	String	tssize	;

    private	BigInteger	tcolorno	;
    private	String	tcolorname	;

    

}
