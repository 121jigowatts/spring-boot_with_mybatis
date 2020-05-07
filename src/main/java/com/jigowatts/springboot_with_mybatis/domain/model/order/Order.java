package com.jigowatts.springboot_with_mybatis.domain.model.order;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private String orderNumber;
    private List<OrderDetail> orderDetails;
}