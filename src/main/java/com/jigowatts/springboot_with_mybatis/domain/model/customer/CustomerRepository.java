package com.jigowatts.springboot_with_mybatis.domain.model.customer;

import java.util.Optional;

public interface CustomerRepository {
    Optional<Customer> findById(String customerId);
}