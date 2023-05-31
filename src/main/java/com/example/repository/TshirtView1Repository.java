package com.example.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entity.TshirtView1;

@Repository
public interface TshirtView1Repository extends JpaRepository<TshirtView1, BigInteger>{
    // TshirtView의 정보를 티셔츠 타입으로 tno내림차순으로 가져오기

    // public List<TshirtView1> findAllByTpriceOrderByTnoDesc(BigInteger tprice);

    // 타입이름으로 
    public List<TshirtView1> findByTtnoOrderByTnoDesc(BigInteger ttno);
    
    // 대표이미지
    TshirtView1 findTop1ByTnoOrderByInoAsc(BigInteger tno);

    // public List<TshirtView1> findAllByOrderByTnoDesc();

    // @Query(value="SELECT * FROM ( SELECT m1.*, ROW_NUMBER() OVER (ORDER BY name DESC) rown FROM MEMBER1 m1 WHERE m1.name LIKE '%' || :name || '%' ) WHERE rown BETWEEN :start AND :end", nativeQuery=true)
    // public List<TshirtView1> selectMinInoByTno(@Param("name") String name, @Param("start") int start, @Param("end") int end );


}
