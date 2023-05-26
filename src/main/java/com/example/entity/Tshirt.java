package com.example.entity;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name = "TSHIRT")
@SequenceGenerator(name = "SEQ_TSHIRT_NO", sequenceName = "SEQ_TSHIRT_NO", initialValue = 1, allocationSize = 1)
public class Tshirt {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TSHIRT_NO")
    @Column
    private	BigInteger	tno	;
    private	String	tname	;
    private	BigInteger	tprice	;
    private	BigInteger	tcno	;
    private	BigInteger	ttno	;
    private	BigInteger	tquantity	;
    
    @ToString.Exclude
    @OneToMany(mappedBy = "tshirt", cascade=CascadeType.REMOVE, fetch=FetchType.LAZY)
    private List<TshirtImage> list = new ArrayList<>();
    
    @ToString.Exclude
    @OneToMany(mappedBy = "tshirt", cascade=CascadeType.REMOVE, fetch=FetchType.LAZY)
    private List<TshirtSize> list1 = new ArrayList<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "tshirt", cascade=CascadeType.REMOVE, fetch=FetchType.LAZY)
    private List<TshirtSize> list2 = new ArrayList<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "tshirt", cascade=CascadeType.REMOVE, fetch=FetchType.LAZY)
    private List<PsidePic> list3 = new ArrayList<>();
}
