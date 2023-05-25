package com.example.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.TshirtColor;

@Repository
public interface TshirtColorRepository extends JpaRepository<TshirtColor, BigInteger>{
    
    List<TshirtColor> findByTshirt_tno(BigInteger tno);
}
