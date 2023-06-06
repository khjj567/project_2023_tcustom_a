package com.example.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entity.TsDesignView;

@Repository
public interface TsDesignViewRepository extends JpaRepository<TsDesignView, BigInteger>{
    
    @Query(value="SELECT * FROM TSDESIGNVIEW ts WHERE ts.mid =:mid AND ts.tno=:tno AND ts.psno=:psno AND ts.tcolorno=:tcolorno AND ts.tsno=:tsno AND ts.pno=:pno AND ts.fno=:fno GROUP BY dno", nativeQuery=true)
    TsDesignView selectOneTsDesignGroupByDno(
        @Param("mid") String mid, 
        @Param("tno") BigInteger tno, 
        @Param("psno") BigInteger psno,
        @Param("tcolorno") BigInteger tcolorno,
        @Param("tsno") BigInteger tsno,
        @Param("pno") BigInteger pno,
        @Param("fno") BigInteger fno
         );

}
