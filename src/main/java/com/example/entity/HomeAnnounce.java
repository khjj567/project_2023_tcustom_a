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

@Data
@Entity
@Table(name = "HOMEANNOUNCE")
@SequenceGenerator(name = "SEQ_HAN_NO", sequenceName = "SEQ_HAN_NO", initialValue = 1000, allocationSize = 1)

public class HomeAnnounce {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_HAN_NO")
    private	BigInteger	hanno	;
    private	String	hantitle	;
    @Lob
    private	String	hancontent	;
    private	BigInteger	hanhit	;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    @CreationTimestamp 
    private	Date	hanregdate	;
    private	BigInteger	managerno	;
}
