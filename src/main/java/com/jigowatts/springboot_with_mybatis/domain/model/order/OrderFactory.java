package com.jigowatts.springboot_with_mybatis.domain.model.order;

import java.util.List;

public interface OrderFactory {
    Order create(List<OrderDetail> orderDetails);
}