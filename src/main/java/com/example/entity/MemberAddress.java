package com.example.entity;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "MEMBERADDRESS")
@SequenceGenerator(name = "SEQ_ADDERSS_NO", sequenceName = "SEQ_ADDERSS_NO", initialValue = 10000, allocationSize = 1)
public class MemberAddress {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ADDERSS_NO")
    @Column(name = "ANO")
    private	BigInteger	ano	;
    private	String	aname	;
    private	String	aphone	;
    private	String	apostcode	;
    private	String	aaddress	;

    @ManyToOne
    @JoinColumn(name="mid", referencedColumnName="mid")
    private Member member;
    
}
