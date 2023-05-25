package com.example.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.TshirtSize;
import com.example.entity.TshirtSizeProjection;


@Repository
public interface TshirtSizeRepository extends JpaRepository<TshirtSize, BigInteger>{

    List<TshirtSize> findByTshirt_tno(BigInteger tno);  
}
