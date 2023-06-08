package com.example.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.HomeAsk;


@Repository
public interface HomeAskRepository extends JpaRepository<HomeAsk, BigInteger>{
    
    List<HomeAsk> findByMid(String mid);
}