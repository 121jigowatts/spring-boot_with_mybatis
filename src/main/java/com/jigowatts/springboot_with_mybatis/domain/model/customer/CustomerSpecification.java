package com.jigowatts.springboot_with_mybatis.domain.model.customer;

public class CustomerSpecification {

    private String customerId;

    public CustomerSpecification(String customerId) {
        this.customerId = customerId;
    }

    public boolean isSatisfied(CustomerRepository repository) {
        var customer = repository.findById(this.customerId);
        return !customer.isEmpty();
    }
}
