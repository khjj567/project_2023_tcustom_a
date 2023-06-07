package com.example.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Tshirt;
import java.util.List;


@Repository
public interface TshirtRepository extends JpaRepository<Tshirt, BigInteger>{
    
    Tshirt findByTno(BigInteger tno);

}
