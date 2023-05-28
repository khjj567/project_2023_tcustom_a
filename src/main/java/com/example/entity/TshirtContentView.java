package com.example.entity;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="TSHIRTCONTENTVIEW")

public class TshirtContentView {
    @Id
    @Column(name = "TNO")
    private	BigInteger	tno	;
    private	String	tname	;
    private	BigInteger	tprice	;
    private	BigInteger	tquantity	;
    
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

    

}
