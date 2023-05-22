package com.example.entity;

import java.math.BigInteger;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.Immutable;

import lombok.Data;

@Data
@Immutable // VIEW entity 만들땐 필수 어노테이션
@Entity
@Table(name = "TSHIRTVIEW1")
public class TshirtView1 {
    // t.TNO , t.TNAME , t.TPRICE , t.TQUANTITY, 
	// 	tt.TTNO , tt.TTNAME 
    @Id
    private BigInteger tno;

    private String tname;
    private BigInteger tprice;
    private BigInteger tquantity;
    private BigInteger ttno;
    private String ttname;
}
