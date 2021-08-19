package com.jigowatts.springboot_with_mybatis.application.component;

import java.util.List;

import com.jigowatts.springboot_with_mybatis.domain.model.customer.Customer;

public interface CustomerComponent {
    List<Customer> getBy(String id);
}
