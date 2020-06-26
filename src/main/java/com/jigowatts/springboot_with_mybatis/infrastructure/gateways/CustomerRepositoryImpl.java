package com.jigowatts.springboot_with_mybatis.infrastructure.gateways;

import java.util.Optional;

import com.jigowatts.springboot_with_mybatis.domain.model.customer.Customer;
import com.jigowatts.springboot_with_mybatis.domain.model.customer.CustomerRepository;
import com.jigowatts.springboot_with_mybatis.infrastructure.gateways.client.CustomerInquiryClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

    @Autowired
    CustomerInquiryClient customerInquiryClient;

    @Override
    public Optional<Customer> findById(String customerId) {
        return customerInquiryClient.getCustomer(customerId);
    }
    
}