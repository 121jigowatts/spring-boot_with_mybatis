package com.jigowatts.springboot_with_mybatis.presentation.resource;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResource {
    private String orderNumber;
    private List<OrderDetailResource> orderDetails;
}