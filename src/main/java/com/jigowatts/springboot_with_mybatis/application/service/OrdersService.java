package com.jigowatts.springboot_with_mybatis.application.service;

import com.jigowatts.springboot_with_mybatis.domain.model.order.Order;

public interface OrdersService {
    String create(Order order);
    
}