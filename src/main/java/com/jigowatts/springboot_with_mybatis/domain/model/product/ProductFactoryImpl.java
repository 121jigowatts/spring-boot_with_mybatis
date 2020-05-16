package com.jigowatts.springboot_with_mybatis.domain.model.product;

import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class ProductFactoryImpl implements ProductFactory {

    @Override
    public Product create(String productName) {
        return Product.builder().productId(UUID.randomUUID().toString()).productName(productName).build();
    }

}