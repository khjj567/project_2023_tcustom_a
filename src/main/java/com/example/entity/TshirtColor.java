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
@Table(name="TCOLOR")
@SequenceGenerator(name = "SEQ_TCOLOR_NO", sequenceName = "SEQ_TCOLOR_NO", initialValue = 1, allocationSize = 1)
public class TshirtColor {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TCOLOR_NO")
    @Column(name="TCOLORNO")
    private BigInteger tcolorno;
    private String tcolorname;
    
    @ManyToOne
    @JoinColumn(name = "tno", referencedColumnName = "tno")
    private Tshirt tshirt;
}
