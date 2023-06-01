package com.example.entity;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.data.annotation.Immutable;

import lombok.Data;
import lombok.ToString;

@Entity
@Data
@Table(name="TSHIRTPRINTINGSIDEPICVIEW")
@Immutable
public class TshirtPrintingSidePicView {
    
    @Id
    @Column(name="PSPICNO")
    private	BigInteger	pspicno	;

    private	BigInteger	tno	;
    private	String	tname	;

    private	BigInteger	psno	;
    private	String	psidename	;

    private	String	pspicname	;
    private	BigInteger	pspicsize	;
    @Lob
    @ToString.Exclude
    private	byte[]	pspicdata	;
    private	String	pspictype	;
        
    @Transient //임시변수 :컬럼이 생성되지 않는다. mybatis의 dto개념 // javax 추가함
    private String imageUrl;
}
