package com.javabhakt.os.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javabhakt.os.api.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>{

}
