package com.example.entity;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "PRINTINGSIDE")
@SequenceGenerator(name = "SEQ_PSIDE_NO", sequenceName = "SEQ_PSIDE_NO", initialValue = 1, allocationSize = 1)
public class PrintingSide {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PSIDE_NO")
    @Column(name="PSNO")
    private BigInteger psno;
    private String psidename;
}   

