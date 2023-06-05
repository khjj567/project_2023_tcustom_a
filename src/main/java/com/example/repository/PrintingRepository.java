package com.example.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Printing;
import java.util.List;


@Repository
public interface PrintingRepository extends JpaRepository<Printing, BigInteger>{
    Printing findByPmethod(String pmethod);
}
