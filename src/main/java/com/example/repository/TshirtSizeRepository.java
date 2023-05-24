package com.example.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.TshirtSize;

@Repository
public interface TshirtSizeRepository extends JpaRepository<TshirtSize, BigInteger>{
    
    List<TshirtSize> findTssizeByTshirt_tno(BigInteger tno);

}
