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
@Table(name = "HOMEFQA")
@SequenceGenerator(name = "SEQ_HFQA_NO", sequenceName = "SEQ_HFQA_NO", initialValue = 1, allocationSize = 1)

public class HomeFqa {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_HFQA_NO")
    private	BigInteger	hfqano	;

    @Lob
    @ToString.Exclude
    private	String	hquestion    ;
    @Lob
    @ToString.Exclude
    private	String	hanswer 	;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    @CreationTimestamp 
    private	Date	haskregdate	;
    
}
