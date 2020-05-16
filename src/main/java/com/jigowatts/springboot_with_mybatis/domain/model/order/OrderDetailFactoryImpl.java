package com.jigowatts.springboot_with_mybatis.domain.model.order;

import java.util.UUID;

import com.jigowatts.springboot_with_mybatis.domain.model.product.Product;

import org.springframework.stereotype.Component;

@Component
public class OrderDetailFactoryImpl implements OrderDetailFactory {

    public OrderDetail create(int quantity, int unitPrice, Product product) {
        return OrderDetail.builder().orderDetailId(UUID.randomUUID().toString()).quantity(quantity).unitPrice(unitPrice)
                .product(product).build();
    }

}