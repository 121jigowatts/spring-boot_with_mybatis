package com.jigowatts.springboot_with_mybatis.infrastructure.gateways.client;

import java.util.Optional;

import com.jigowatts.springboot_with_mybatis.domain.model.customer.Customer;

public interface CustomerInquiryClient {
    Optional<Customer> getCustomer(String customerId);
}