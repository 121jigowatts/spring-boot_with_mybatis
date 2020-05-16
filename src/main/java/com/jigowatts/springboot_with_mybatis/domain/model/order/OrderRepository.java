package com.jigowatts.springboot_with_mybatis.domain.model.order;

import java.util.Optional;

public interface OrderRepository {
    Optional<Order> findByOrderNumber(String orderNumber);
    String create(Order order);
}