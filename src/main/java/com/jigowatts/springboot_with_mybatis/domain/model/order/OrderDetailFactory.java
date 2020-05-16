package com.jigowatts.springboot_with_mybatis.domain.model.order;

import com.jigowatts.springboot_with_mybatis.domain.model.product.Product;

public interface OrderDetailFactory {
    OrderDetail create(int quantity, int unitPrice, Product product);
}