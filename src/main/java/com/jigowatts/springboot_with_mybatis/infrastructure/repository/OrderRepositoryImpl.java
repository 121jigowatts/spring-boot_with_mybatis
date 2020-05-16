package com.jigowatts.springboot_with_mybatis.infrastructure.repository;

import java.util.Optional;

import com.jigowatts.springboot_with_mybatis.domain.model.order.Order;
import com.jigowatts.springboot_with_mybatis.domain.model.order.OrderDetail;
import com.jigowatts.springboot_with_mybatis.domain.model.order.OrderRepository;
import com.jigowatts.springboot_with_mybatis.infrastructure.entity.OrderDetails;
import com.jigowatts.springboot_with_mybatis.infrastructure.entity.OrdersKey;
import com.jigowatts.springboot_with_mybatis.infrastructure.mapper.OrderDetailsMapper;
import com.jigowatts.springboot_with_mybatis.infrastructure.mapper.OrdersMapper;
import com.jigowatts.springboot_with_mybatis.infrastructure.mapper.extensions.OrdersExtensionsMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class OrderRepositoryImpl implements OrderRepository {

    @Autowired
    OrdersMapper ordersMapper;

    @Autowired
    OrderDetailsMapper orderDetailsMapper;
    @Autowired
    OrdersExtensionsMapper ordersExtensionsMapper;

    private String orderNumber;

    @Override
    public Optional<Order> findByOrderNumber(String orderNumber) {
        return ordersExtensionsMapper.findByOrderNumber(orderNumber);
    }

    @Override
    public String create(Order order) {
        this.orderNumber = order.getOrderNumber();
        order.getOrderDetails().stream().forEach(this::saveOrder);
        return this.orderNumber;
    }

    private void saveOrder(OrderDetail od) {
        String orderDetailId = od.getOrderDetailId();
        int quantity = od.getQuantity();
        int unitPrice = od.getUnitPrice();
        String productId = od.getProduct().getProductId();

        OrderDetails orderDetailsRecord = OrderDetails.builder().orderDetailId(orderDetailId).quantity(quantity)
                .unitPrice(unitPrice).productId(productId).build();
        orderDetailsMapper.insert(orderDetailsRecord);

        OrdersKey ordersKeyRecord = OrdersKey.builder().orderNumber(this.orderNumber).orderDetailId(orderDetailId)
                .build();
        ordersMapper.insert(ordersKeyRecord);
    }

}