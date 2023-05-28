package com.example.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.TshirtImage;

@Repository
public interface TshirtImageRepository extends JpaRepository<TshirtImage, BigInteger>{
    
        // findBy 변수명_하위변수
    // _는 하위에 있는 변수 : menu에서 phone을 가져오려면 레스토랑1안에 있다
    List<TshirtImage> findByTshirt_tno(BigInteger tno);

    // 대표이미지
    TshirtImage findTop1ByTshirt_tnoOrderByInoAsc(BigInteger tno);
    
    // 전체이미지
    List<TshirtImage> findByTshirt_tnoOrderByInoAsc(BigInteger tno);
}
