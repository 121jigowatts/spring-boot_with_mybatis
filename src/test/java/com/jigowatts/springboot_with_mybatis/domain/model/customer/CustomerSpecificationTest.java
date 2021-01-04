package com.jigowatts.springboot_with_mybatis.domain.model.customer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CustomerSpecificationTest {

    CustomerSpecification target;
    @Mock
    CustomerRepository repository;

    @BeforeEach
    void init() {
        this.target = new CustomerSpecification("customerId");
    }

    @Test
    public void isSatisfiedTest_true() {
        Optional<Customer> customer = Optional.of(Customer.builder().customerId("customerId").build());
        doReturn(customer).when(repository).findById(any());
        
        var actual = target.isSatisfied(this.repository);
        assertThat(actual).isTrue();
    }

    @Test
    public void isSatisfiedTest_false() {
        Optional<Customer> customer = Optional.empty();
        doReturn(customer).when(repository).findById(any());

        var actual = target.isSatisfied(this.repository);
        assertThat(actual).isFalse();
    }
}
