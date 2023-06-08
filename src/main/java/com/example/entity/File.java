package com.example.entity;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.ToString;

@Data
@Table(name="FILE")
@Entity
@SequenceGenerator(name = "SEQ_FILE_NO", sequenceName = "SEQ_FILE_NO", initialValue = 1, allocationSize = 1)
public class File {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_FILE_NO")
    private BigInteger fno; // 파일번호

    private	String	fname	; // 파일명
    private	BigInteger	fsize	; // 파일사이즈
    
    @Lob
    @ToString.Exclude 
    private byte[] 	fdata	; // 파일데이터

    private	String	ftype	; // 파일타입

    @Transient
    private String imageUrl1;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    @CreationTimestamp 
    private Date fregdate	; // 파일등록일

    @ToString.Exclude
    @OneToMany(mappedBy = "file", cascade=CascadeType.REMOVE, fetch=FetchType.LAZY)
    private List<DesignOne> list4 = new ArrayList<>();
}
