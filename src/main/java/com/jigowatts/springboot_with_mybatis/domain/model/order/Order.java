package com.jigowatts.springboot_with_mybatis.domain.model.order;

import java.util.List;

import com.jigowatts.springboot_with_mybatis.domain.model.customer.Customer;

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
    private Customer customer;
}