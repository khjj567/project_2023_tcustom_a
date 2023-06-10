package com.example.repository;

import java.math.BigInteger;

import javax.transaction.Transactional;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.entity.HomeAnnounce;

@Repository
public interface HomeAnnounceRepository extends JpaRepository<HomeAnnounce, BigInteger>{
    
    HomeAnnounce findByHanno(BigInteger hanno);

    @Transactional
    @Modifying
    @Query("UPDATE HomeAnnounce h SET h.hanhit = h.hanhit + 1 WHERE h.hanno=:hanno")
    void updateHanhitCount(@Param("hanno") BigInteger hanno);
}
