package com.example.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.TshirtDesignView0;
import com.example.entity.TshirtView01;

@Repository
public interface TshirtDesignView0Repository extends JpaRepository<TshirtDesignView0, BigInteger>{

    TshirtDesignView0 findByPmethodAndTno(String Pmethod, BigInteger tno);

}
