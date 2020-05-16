package com.jigowatts.springboot_with_mybatis.application.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

import com.jigowatts.springboot_with_mybatis.domain.model.order.Order;
import com.jigowatts.springboot_with_mybatis.domain.model.order.OrderRepository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OrdersServiceImplTest {
    @InjectMocks
    private OrdersServiceImpl target;
    @Mock
    private OrderRepository orderRepository;

    @Test
    @DisplayName("Orderの保存")
    public void createTest() {
        String expected = "orderNumber";
        Order order = Order.builder().orderNumber(expected).build();
        doReturn(expected).when(orderRepository).create(any(Order.class));

        String actual = target.create(order);
        assertThat(actual).isEqualTo(expected);
    }
}