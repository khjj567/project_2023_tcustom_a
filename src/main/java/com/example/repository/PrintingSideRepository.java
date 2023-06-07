package com.example.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.PrintingSide;
import java.util.List;


@Repository
public interface PrintingSideRepository extends JpaRepository<PrintingSide, BigInteger>{
    // 프린팅사이드이름(유일)으로 psno를 찾을것
    PrintingSide findByPsidename(String psidename);

    PrintingSide findByPsno(BigInteger psno);
}
