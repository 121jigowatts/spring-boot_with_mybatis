package com.jigowatts.springboot_with_mybatis.domain.model.order;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class OrderFactoryImpl implements OrderFactory {

    @Override
    public Order create(List<OrderDetail> orderDetails) {
        return Order.builder().orderNumber(UUID.randomUUID().toString()).orderDetails(orderDetails).build();
    }
}