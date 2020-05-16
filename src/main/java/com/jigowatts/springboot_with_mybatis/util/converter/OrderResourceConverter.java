package com.jigowatts.springboot_with_mybatis.util.converter;

import java.util.List;

import com.jigowatts.springboot_with_mybatis.domain.model.order.Order;
import com.jigowatts.springboot_with_mybatis.domain.model.order.OrderDetail;
import com.jigowatts.springboot_with_mybatis.domain.model.order.OrderFactory;
import com.jigowatts.springboot_with_mybatis.presentation.resource.OrderDetailResource;
import com.jigowatts.springboot_with_mybatis.presentation.resource.OrderResource;
import com.jigowatts.springboot_with_mybatis.presentation.resource.converter.ResourceConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderResourceConverter implements ResourceConverter<OrderResource, Order> {

    @Autowired
    OrderDetailResourceConverter orderDetailResourceConverter;
    @Autowired
    OrderFactory orderFactory;

    @Override
    public Order toEntity(OrderResource resource) {

        if (resource == null) {
            return null;
        }

        List<OrderDetail> orderDetails = orderDetailResourceConverter.toEntityList(resource.getOrderDetails());
        if (resource.getOrderNumber() == null) {
            return orderFactory.create(orderDetails);
        }

        return Order.builder()
        .orderNumber(resource.getOrderNumber())
        .orderDetails(orderDetails)
        .build();
    }

    @Override
    public OrderResource toResource(Order entity) {
        if (entity == null) {
            return null;
        }

        List<OrderDetail> orderDetails = entity.getOrderDetails();
        List<OrderDetailResource> orderDetailsResource = orderDetailResourceConverter.toResourceList(orderDetails);

        return OrderResource.builder().orderNumber(entity.getOrderNumber()).orderDetails(orderDetailsResource).build();
    }

}