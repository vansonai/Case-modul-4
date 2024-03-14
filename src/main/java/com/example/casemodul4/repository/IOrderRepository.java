package com.example.casemodul4.repository;

import com.example.casemodul4.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderRepository extends JpaRepository<Order, Long> {
}
