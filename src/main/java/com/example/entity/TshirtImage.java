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
    private	BigInteger	tno	;

    
}