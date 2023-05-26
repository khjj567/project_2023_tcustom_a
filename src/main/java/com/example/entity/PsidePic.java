package com.example.entity;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name="PSIDEPIC")
@SequenceGenerator(name = "SEQ_PSPIC_NO", sequenceName = "SEQ_PSPIC_NO", initialValue = 1, allocationSize = 1)
public class PsidePic {
    @Id
    @Column(name="PSPICNO")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PSPIC_NO")
    private	BigInteger	pspicno	;
    private	String	pspicname	;
    private	BigInteger	pspicsize	;
    @Lob
    @ToString.Exclude
    private	byte[]	pspicdata	;
    private	String	pspictype	;

    @ManyToOne
    @JoinColumn(name = "tno", referencedColumnName = "tno")
    private Tshirt tshirt;

    @ManyToOne
    @JoinColumn(name = "psno", referencedColumnName = "psno")
    private PrintingSide printingSide;
    
    
}
