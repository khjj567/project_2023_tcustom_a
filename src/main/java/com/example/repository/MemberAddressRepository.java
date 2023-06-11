package com.example.repository;

import java.math.BigInteger;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entity.Member;
import com.example.entity.MemberAddress;


@Repository
public interface MemberAddressRepository extends JpaRepository<MemberAddress, BigInteger>{
    
    List<MemberAddress> findByMember(Member member);
    MemberAddress findByAno(BigInteger ano);

    @Transactional
    @Modifying
    @Query("DELETE FROM MemberAddress a WHERE a.ano = :ano")
    void deleteByAno(@Param("ano") BigInteger ano);
}
