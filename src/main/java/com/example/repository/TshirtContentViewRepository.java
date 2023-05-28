package com.example.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.TshirtContentView;

@Repository
public interface TshirtContentViewRepository extends JpaRepository<TshirtContentView, BigInteger>{
    
    TshirtContentView findByTno(BigInteger tno); 
}
