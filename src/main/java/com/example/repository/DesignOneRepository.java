package com.example.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.DesignOne;

@Repository
public interface DesignOneRepository extends JpaRepository<DesignOne, BigInteger> {
    
    DesignOne findByDno(BigInteger dno);

    List<DesignOne> findByMember_MidOrderByDnoDesc(String mid);

    // List<DesignOne> findByMember_MidOrderByDnoDesc(String mid);

}
