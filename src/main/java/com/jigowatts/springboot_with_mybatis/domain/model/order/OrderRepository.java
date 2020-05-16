package com.jigowatts.springboot_with_mybatis.domain.model.order;

public interface OrderRepository {
    String create(Order order);
}