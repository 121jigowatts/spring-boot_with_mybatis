package com.jigowatts.springboot_with_mybatis.infrastructure.mapper.extensions;

import java.util.Optional;

import com.jigowatts.springboot_with_mybatis.domain.model.order.Order;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrdersExtensionsMapper {
    Optional<Order> findByOrderNumber(String orderNumber);
}