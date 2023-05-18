package com.example.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.TshirtDesignView;

@Repository
public interface TshirtDesignViewRepository  extends JpaRepository<TshirtDesignView, BigInteger>{
    

    public List<TshirtDesignView> findByMidOrderByDregdateDesc(String mid);
}
