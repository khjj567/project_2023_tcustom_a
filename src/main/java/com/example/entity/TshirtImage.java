package com.example.entity;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name = "TSHIRTIMAGE")
@SequenceGenerator(name = "SEQ_T_IMAGE_NO", sequenceName = "SEQ_T_IMAGE_NO", initialValue = 1, allocationSize = 1)
public class TshirtImage {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_T_IMAGE_NO")
    @Column
    private	BigInteger	ino	;

    private	String	iname	;
    private	BigInteger	isize	;

    @Lob 
    @ToString.Exclude 
    private	byte[]	idata	;

    private	String	itype	;

    @ManyToOne
    @JoinColumn(name = "tno", referencedColumnName = "tno")
    private Tshirt tshirt;
    
    @Transient //임시변수 :컬럼이 생성되지 않는다. mybatis의 dto개념 // javax 추가함
    private String imageUrl;

}
