package com.example.entity;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.Immutable;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name="MEMBERFILEVIEW")
@Immutable
public class MemberFileView {

    @Id
    @Column(name="DNO")
    private	BigInteger	dno	;
    private	String	mid	;
    private	BigInteger	fno	;
    private	String	mname	;
    private	String	fname	;
    private	BigInteger	fsize	;
    @Lob
    @ToString.Exclude
    private	byte[]	fdata	;
    private	String	ftype	;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    @CreationTimestamp 
    private	Date	fregdate	;

}
