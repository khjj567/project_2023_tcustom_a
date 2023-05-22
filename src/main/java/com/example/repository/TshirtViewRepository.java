package com.example.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.TshirtView;



@Repository
public interface TshirtViewRepository extends JpaRepository<TshirtView, BigInteger>{
    
}
