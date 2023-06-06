package com.example.entity;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "ORDERS")
@SequenceGenerator(name = "SEQ_ORDER_NO", sequenceName = "SEQ_ORDER_NO", initialValue = 10000, allocationSize = 1)
public class Orders {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ORDER_NO")
    @Column(name = "ONO")
    private	BigInteger	ono	; // 자동
    private	BigInteger	ocnt	; 
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    @CreationTimestamp // 변경 시 날짜 정보 변경
    private	Date	oregdate	; // 자동
    private	BigInteger	ocondition	; // 자동

    @ManyToOne
    @JoinColumn(name = "dno", referencedColumnName = "dno")
    private DesignOne designOne;
}