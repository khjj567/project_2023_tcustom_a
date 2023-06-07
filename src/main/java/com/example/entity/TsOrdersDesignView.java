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
@Table(name = "TSORDERSDESIGNVIEW")
@Immutable
public class TsOrdersDesignView {

    @Id
    @Column(name="DNO")
    private	BigInteger	dno	;

    private	String	mid	;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    @CreationTimestamp 
    private	Date	dregdate	;

    private	BigInteger	pno	;
    private	BigInteger	pprice	;
    private	String	pmethod	;

    private	BigInteger	fno	;
    private	String	fname	;
    private	BigInteger	fsize	;
    @Lob
    @ToString.Exclude
    private	byte[]	fdata	;
    private	String	ftype	;

    private	BigInteger	psno	;
    private	String	psidename	;

    private	BigInteger	tno	;
    private	String	tname	;
    private	BigInteger	tprice	;
    private	BigInteger	tquantity	;

    private	BigInteger	tsno	;
    private	String	tssize	;
    
    private	BigInteger	tcolorno	;
    private	String	tcolorname	;
    
    private	BigInteger	ono	;
    private	BigInteger	ocnt	;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    @CreationTimestamp 
    private	Date	oregdate	;

    private	BigInteger	ocondition	;

    private BigInteger totalprice;
}
