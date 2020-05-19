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
            Customer c = o.getCustomer();
            String customerId = c.getCustomerId();
            Optional<Customer> customer = customerRepository.findById(customerId);
            customer.ifPresent(o::setCustomer);
        });
        return order;
    }

    @Override
    public String create(Order order) {
        return orderRepository.create(order);
    }

}