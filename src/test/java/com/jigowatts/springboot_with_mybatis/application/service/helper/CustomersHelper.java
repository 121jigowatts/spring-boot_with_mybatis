package com.jigowatts.springboot_with_mybatis.application.service.helper;

import java.util.List;

import com.jigowatts.springboot_with_mybatis.domain.model.customer.Customer;

public class CustomersHelper {
    public static List<Customer> customers_id_1() {
        return List.of(Customer.builder().customerId("1").customerName("customer1").build(),
                Customer.builder().customerId("2").customerName("customer2").build());
    }
    public static List<Customer> customers_id_2() {
        return List.of(Customer.builder().customerId("3").customerName("customer3").build(),
                Customer.builder().customerId("4").customerName("customer4").build());
    }
}
