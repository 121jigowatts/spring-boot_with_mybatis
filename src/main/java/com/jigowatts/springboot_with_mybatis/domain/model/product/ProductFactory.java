package com.jigowatts.springboot_with_mybatis.domain.model.product;

public interface ProductFactory {
    Product create(String productName);
}