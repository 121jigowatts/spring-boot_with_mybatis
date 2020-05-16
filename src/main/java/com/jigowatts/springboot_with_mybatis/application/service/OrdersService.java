package com.jigowatts.springboot_with_mybatis.application.service;

import java.util.Optional;

import com.jigowatts.springboot_with_mybatis.domain.model.order.Order;

public interface OrdersService {
    Optional<Order> findByOrderNumber(String orderNumber);
    String create(Order order);
}