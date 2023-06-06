package com.example.entity;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name = "DESIGNONE")
@SequenceGenerator(name = "SEQ_DESIGN_NO", sequenceName = "SEQ_DESIGN_NO", initialValue = 1, allocationSize = 1)
public class DesignOne {
    @Id
    @Column(name = "DNO")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_DESIGN_NO")
    private	BigInteger	dno	;
    private	BigInteger	tno	;
    private	BigInteger	fno	;
    private	BigInteger	pno	;
    private	BigInteger	psno	;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    @CreationTimestamp // 변경 시 날짜 정보 변경
    private	Date dregdate	;
    
    @ManyToOne
    @JoinColumn(name="mid", referencedColumnName="mid")
    private Member member;

    @ToString.Exclude
    @OneToMany(mappedBy = "designOne", cascade=CascadeType.REMOVE, fetch=FetchType.LAZY)
    private List<Orders> list = new ArrayList<>();
}
