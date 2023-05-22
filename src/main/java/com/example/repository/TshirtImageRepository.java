package com.example.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.TshirtImage;

@Repository
public interface TshirtImageRepository extends JpaRepository<TshirtImage, BigInteger>{
    
    
}
