package com.jigowatts.springboot_with_mybatis.presentation.resource;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailResource {
    private String orderDetailId;
    private int quantity;
    private int unitPrice;
    private ProductResource product;
}