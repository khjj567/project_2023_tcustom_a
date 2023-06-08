package com.example.entity;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name = "HOMEASK")
@SequenceGenerator(name = "SEQ_HASK_NO", sequenceName = "SEQ_HASK_NO", initialValue = 1000, allocationSize = 1)

public class HomeAsk {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_HASK_NO")
    private	BigInteger	haskno	;
    private	String	hasktitle	;
    @Lob
    @ToString.Exclude
    private	String	haskcontent	;
    private	BigInteger	haskhit ;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    @CreationTimestamp 
    private	Date	haskregdate	;
    private	String	mid	;
    
}
