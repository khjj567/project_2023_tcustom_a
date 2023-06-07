package com.example.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Orders;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, BigInteger>{
    
    List<Orders> findByDesignOne_Member_MidOrderByOno(String mid);

    Orders findByOno(BigInteger ono);
}
