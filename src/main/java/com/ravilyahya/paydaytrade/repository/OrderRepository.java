package com.ravilyahya.paydaytrade.repository;

import com.ravilyahya.paydaytrade.model.Order;
import com.ravilyahya.paydaytrade.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> getOrdersByUserAndIsActive(User user,Boolean isActive);
}
