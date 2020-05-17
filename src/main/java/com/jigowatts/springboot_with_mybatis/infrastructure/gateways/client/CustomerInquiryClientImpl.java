package com.jigowatts.springboot_with_mybatis.infrastructure.gateways.client;

import java.util.Optional;

import com.jigowatts.springboot_with_mybatis.domain.model.customer.Customer;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

public class CustomerInquiryClientImpl extends WebServiceGatewaySupport implements CustomerInquiryClient {

    @Override
    public Optional<Customer> getCustomer(String customerId) {
        // TODO Auto-generated method stub
        return null;
    }

}