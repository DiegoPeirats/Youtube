package com.example.demo.infrastructure.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

	List<Order> findAllByUserId (Long userId);
}
