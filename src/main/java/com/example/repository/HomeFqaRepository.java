package com.example.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.HomeAsk;
import com.example.entity.HomeFqa;

@Repository
public interface HomeFqaRepository extends JpaRepository<HomeFqa, BigInteger>{
    
}
