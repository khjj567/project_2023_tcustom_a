package com.example.entity;

import java.math.BigInteger;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.data.annotation.Immutable;

import lombok.Data;
import lombok.ToString;

@Data
@Immutable // VIEW entity 만들땐 필수 어노테이션
@Entity
@Table(name = "TSHIRTVIEW4")
public class TshirtView4 {

    @Id
    private	BigInteger	ino	;
    
    private BigInteger tno;

    private String tname;
    private BigInteger tprice;

    private BigInteger tquantity;
    //

    private BigInteger ttno;
    private String ttname;

    //
    // private	BigInteger	ino	;
    private	String	iname	;
    private	BigInteger	isize	;
    @Lob
    @ToString.Exclude
    private	byte[]	idata	;
    private	String	itype	;

    @Transient //임시변수 :컬럼이 생성되지 않는다. mybatis의 dto개념 // javax 추가함
    private String imageUrl;

    private long main;

}
