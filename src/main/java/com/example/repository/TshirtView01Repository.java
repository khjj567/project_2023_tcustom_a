package com.example.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.TshirtView01;

@Repository
public interface TshirtView01Repository extends JpaRepository<TshirtView01, BigInteger>{
    
    TshirtView01 findByTcolornameAndTnoAndTssize(String tcolorname, BigInteger tno, String tssize);

    TshirtView01 findByTssizeAndTno(String tssize, BigInteger tno);

    // TshirtView0 findByPmethodAndTno(String Pmethod, BigInteger tno);

}
