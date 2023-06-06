package com.example.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.DesignOne;

@Repository
public interface DesignOneRepository extends JpaRepository<DesignOne, BigInteger> {
    
    DesignOne findByDno(BigInteger dno);
}