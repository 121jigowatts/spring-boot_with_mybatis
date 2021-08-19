package com.jigowatts.springboot_with_mybatis.application.component;

import java.util.List;

import com.jigowatts.springboot_with_mybatis.domain.model.customer.Customer;

import org.springframework.stereotype.Component;

@Component
public class CustomerComponentImpl implements CustomerComponent {

    @Override
    public List<Customer> getBy(String id) {
        // TODO Auto-generated method stub
        return List.of();
    }

}
