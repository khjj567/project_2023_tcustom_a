package com.example.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.TshirtView4;

@Repository
public interface TshirtView4Repository extends JpaRepository<TshirtView4, BigInteger>{

    // TshirtView4 FindByTno(BigInteger Tno);
    // TshirtView4 findByMain(BigInteger tno);
    // 타입이름으로 
    public List<TshirtView4> findByTtnoOrderByTnoDesc(BigInteger ttno);

}
