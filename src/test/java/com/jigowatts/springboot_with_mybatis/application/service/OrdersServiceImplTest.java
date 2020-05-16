package com.jigowatts.springboot_with_mybatis.application.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;

import java.util.List;
import java.util.Optional;

import com.jigowatts.springboot_with_mybatis.domain.model.order.Order;
import com.jigowatts.springboot_with_mybatis.domain.model.order.OrderDetail;
import com.jigowatts.springboot_with_mybatis.domain.model.order.OrderRepository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OrdersServiceImplTest {
    @InjectMocks
    OrdersServiceImpl target;
    @Mock
    OrderRepository orderRepository;

    @Test
    @DisplayName("Order取得")
    public void findByOrderNumberTest() {
        String orderNumber = "orderNumber";
        List<OrderDetail> orderDetails = List.of(OrderDetail.builder().build());
        Order expected = Order.builder().orderNumber("orderNumber").orderDetails(orderDetails).build();
        Optional<Order> returnedOrder = Optional.ofNullable(expected);

        doReturn(returnedOrder).when(orderRepository).findByOrderNumber(orderNumber);

        Optional<Order> optActual = target.findByOrderNumber(orderNumber);

        optActual.ifPresentOrElse(actual -> {
            assertThat(actual.getOrderNumber()).isEqualTo(expected.getOrderNumber());
            assertThat(actual.getOrderDetails()).isEqualTo(expected.getOrderDetails());
        }, () -> {
            fail();
        });
    }

    @Test
    @DisplayName("Order取得不可")
    public void findByOrderNumberNoDataTest() {
        Optional<Order> returnedNoData = Optional.empty();
        doReturn(returnedNoData).when(orderRepository).findByOrderNumber(anyString());

        Optional<Order> optActual = target.findByOrderNumber("orderNumber");

        assertThat(optActual.isPresent()).isFalse();
    }

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