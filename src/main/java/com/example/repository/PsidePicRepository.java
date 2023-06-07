package com.example.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.PsidePic;
import java.util.List;


@Repository
public interface PsidePicRepository extends JpaRepository<PsidePic, BigInteger>{

    PsidePic findByPspicno(BigInteger pspicno);

    PsidePic findByPrintingSide_PsnoAndTshirt_Tno(BigInteger psno, BigInteger tno);
    
}
