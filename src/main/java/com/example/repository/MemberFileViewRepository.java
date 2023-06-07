package com.example.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.MemberFileView;

@Repository
public interface MemberFileViewRepository extends JpaRepository<MemberFileView, BigInteger>{
    List<MemberFileView> findByMidOrderByDnoDesc(String mid);
}
