package com.example.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.TsDesignView;

@Repository
public interface TsDesignViewRepository extends JpaRepository<TsDesignView, BigInteger>{

    // TsDesignView findByTcolornameAndTno(String tcolorname, BigInteger tno);

    // TsDesignView findByTssizeAndTno(String tssize, BigInteger tno);

    // TsDesignView findByPmethodAndTno(String Pmethod, BigInteger tno);

    
}
