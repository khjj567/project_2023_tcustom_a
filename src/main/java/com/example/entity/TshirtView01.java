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
@Table(name = "TSHIRTVIEW01")
public class TshirtView01 {
    
    @Id
    @Column(name = "TNO")
    private	BigInteger	tno	;
    private	String	tname	;
    private	BigInteger	tprice	;
    private	BigInteger	tquantity	;

    private	BigInteger	tsno	;
    private	String	tssize	;
    
    private	BigInteger	tcolorno	;
    private	String	tcolorname	;

}
