package com.example.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.TsOrdersDesignView;

@Repository
public interface TsOrdersDesignViewRepository extends JpaRepository<TsOrdersDesignView, BigInteger>{

    List<TsOrdersDesignView> findByMidOrderByOnoDesc(String mid);
    
}
