package com.jigowatts.springboot_with_mybatis.domain.model.order;

import com.jigowatts.springboot_with_mybatis.domain.model.product.Product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetail {
    private String orderDetailId;
    private int quantity;
    private int unitPrice;
    private Product product;
}