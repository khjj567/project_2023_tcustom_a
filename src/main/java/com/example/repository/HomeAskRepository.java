package com.example.repository;

import java.math.BigInteger;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entity.HomeAsk;


@Repository
public interface HomeAskRepository extends JpaRepository<HomeAsk, BigInteger>{
    
    List<HomeAsk> findByMid(String mid);
    HomeAsk findByHaskno(BigInteger haskno);

    @Transactional
    @Modifying
    @Query("UPDATE HomeAsk h SET h.haskhit = h.haskhit + 1 WHERE h.haskno=:haskno")
    void updateHaskhitCount(@Param("haskno") BigInteger haskno);
}
