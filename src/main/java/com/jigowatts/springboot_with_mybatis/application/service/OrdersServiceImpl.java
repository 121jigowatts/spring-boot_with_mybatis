package com.jigowatts.springboot_with_mybatis.application.service;

import java.util.Optional;

import com.jigowatts.springboot_with_mybatis.domain.model.customer.Customer;
import com.jigowatts.springboot_with_mybatis.domain.model.customer.CustomerRepository;
import com.jigowatts.springboot_with_mybatis.domain.model.order.Order;
import com.jigowatts.springboot_with_mybatis.domain.model.order.OrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    CustomerRepository customerRepository;

    @Override
    public Optional<Order> findByOrderNumber(String orderNumber) {
        Optional<Order> order = orderRepository.findByOrderNumber(orderNumber);
        order.ifPresent(o -> {
            // CustomerId取得
            String customerId = "X3";
            Optional<Customer> customer = customerRepository.findById(customerId);
            // orderにセット
            // return
        });
        return orderRepository.findByOrderNumber(orderNumber);
    }

    @Override
    public String create(Order order) {
        return orderRepository.create(order);
    }

}