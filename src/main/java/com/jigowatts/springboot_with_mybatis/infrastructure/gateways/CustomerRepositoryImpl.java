package com.jigowatts.springboot_with_mybatis.infrastructure.gateways;

import java.util.Optional;

import com.jigowatts.springboot_with_mybatis.domain.model.customer.Customer;
import com.jigowatts.springboot_with_mybatis.domain.model.customer.CustomerRepository;

public class CustomerRepositoryImpl implements CustomerRepository {

    @Override
    public Optional<Customer> findById(String customerId) {
        // TODO Auto-generated method stub
        return null;
    }
    
}