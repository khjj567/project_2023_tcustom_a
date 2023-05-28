package com.example.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.TshirtView2;

@Repository
public interface TshirtView2Repository extends JpaRepository<TshirtView2, BigInteger>{
    // TshirtView의 정보를 티셔츠 타입으로 tno내림차순으로 가져오기

    // public List<TshirtView1> findAllByTpriceOrderByTnoDesc(BigInteger tprice);

    // 타입이름으로 
    public List<TshirtView2> findByTtnoOrderByTnoDesc(BigInteger ttno);

    // public List<TshirtView1> findAllByOrderByTnoDesc();

}
