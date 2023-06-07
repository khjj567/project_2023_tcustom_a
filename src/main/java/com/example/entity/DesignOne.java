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
import javax.persistence.Transient;

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
    
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    @CreationTimestamp // 변경 시 날짜 정보 변경
    private	Date dregdate	;

    @Transient //임시변수 :컬럼이 생성되지 않는다. mybatis의 dto개념 // javax 추가함
    private String tcolorname;
    
    @ManyToOne
    @JoinColumn(name="mid", referencedColumnName="mid")
    private Member member;

    @ManyToOne
    @JoinColumn(name="tno", referencedColumnName="tno")
    private Tshirt tshirt;

    @ManyToOne
    @JoinColumn(name="fno", referencedColumnName="fno")
    private File file;

    @ManyToOne
    @JoinColumn(name="pno", referencedColumnName="pno")
    private Printing printing;

    @ManyToOne
    @JoinColumn(name="psno", referencedColumnName="psno")
    private PrintingSide printingSide;

    @ToString.Exclude
    @OneToMany(mappedBy = "designOne", cascade=CascadeType.REMOVE, fetch=FetchType.LAZY)
    private List<Orders> list = new ArrayList<>();

    private BigInteger tcolorno; // 티셔츠컬러
    private BigInteger tsno; // 티셔츠사이즈
}
