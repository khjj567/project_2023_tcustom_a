package com.example.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Printing;

@Repository
public interface PrintingRepository extends JpaRepository<Printing, BigInteger>{
    
}
