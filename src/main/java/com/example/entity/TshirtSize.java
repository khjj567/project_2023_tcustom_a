package com.example.entity;

import java.math.BigInteger;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;

@Data
@Entity
@Table(name = "TSHIRTSIZE")
@SequenceGenerator(name = "SEQ_TSHIRTSIZE_NO", sequenceName = "SEQ_TSHIRTSIZE_NO", initialValue = 1, allocationSize = 1)
public class TshirtSize {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TSHIRTSIZE_NO")
    private BigInteger tsno;
    private String tssize;  // tssize값 중에서 첫번째 값을 가지고 오고 싶다면? ="[0]";

    @ManyToOne
    @JoinColumn(name = "tno", referencedColumnName = "tno")
    private Tshirt tshirt;

    
}
