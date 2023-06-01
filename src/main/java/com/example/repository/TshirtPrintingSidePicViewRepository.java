package com.example.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.TshirtPrintingSidePicView;

@Repository
public interface TshirtPrintingSidePicViewRepository extends JpaRepository<TshirtPrintingSidePicView, BigInteger>{
    
    // tno로 찾는데
    // OrderByPspicnoDesc : pspicno(id)로 정렬 
    List<TshirtPrintingSidePicView> findByTno(BigInteger tno);

    // psno와 tno로 찾아서
    List<TshirtPrintingSidePicView> findByPsnoAndTno(BigInteger psno, BigInteger tno);
    
}
