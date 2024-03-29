package com.example.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.File;

@Repository
public interface FileRepository extends JpaRepository<File, BigInteger>{
    
    File findByFno(BigInteger fno);
}
