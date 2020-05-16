package com.jigowatts.springboot_with_mybatis.infrastructure.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;

import java.util.List;
import java.util.Optional;

import com.jigowatts.springboot_with_mybatis.domain.model.order.Order;
import com.jigowatts.springboot_with_mybatis.domain.model.order.OrderDetail;
import com.jigowatts.springboot_with_mybatis.domain.model.product.Product;
import com.jigowatts.springboot_with_mybatis.infrastructure.entity.OrderDetails;
import com.jigowatts.springboot_with_mybatis.infrastructure.entity.OrdersKey;
import com.jigowatts.springboot_with_mybatis.infrastructure.mapper.OrderDetailsMapper;
import com.jigowatts.springboot_with_mybatis.infrastructure.mapper.OrdersMapper;
import com.jigowatts.springboot_with_mybatis.infrastructure.mapper.extensions.OrdersExtensionsMapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OrderRepositoryImplTest {
    @InjectMocks
    OrderRepositoryImpl target;
    @Mock
    OrdersMapper ordersMapper;
    @Mock
    OrderDetailsMapper orderDetailsMapper;
    @Mock
    OrdersExtensionsMapper ordersExtensionsMapper;

    @Test
    @DisplayName("Order取得")
    public void findByOrderNumberTest() {
        String orderNumber = "orderNumber";
        List<OrderDetail> orderDetails = List.of(OrderDetail.builder().build());
        Order expected = Order.builder().orderNumber("orderNumber").orderDetails(orderDetails).build();
        Optional<Order> returnedOrder = Optional.ofNullable(expected);

        doReturn(returnedOrder).when(ordersExtensionsMapper).findByOrderNumber(orderNumber);

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
        doReturn(returnedNoData).when(ordersExtensionsMapper).findByOrderNumber(anyString());

        Optional<Order> optActual = target.findByOrderNumber("orderNumber");

        assertThat(optActual.isPresent()).isFalse();
    }

    @Test
    @DisplayName("Order保存")
    public void createTest() {
        doReturn(1).when(ordersMapper).insert(any(OrdersKey.class));
        doReturn(1).when(orderDetailsMapper).insert(any(OrderDetails.class));

        Product product = Product.builder().productId("productId").productName("productName").build();
        OrderDetail orderDetail = OrderDetail.builder().orderDetailId("orderDetailId").quantity(1).unitPrice(100)
                .product(product).build();
        List<OrderDetail> orderDetails = List.of(orderDetail);
        Order order = Order.builder().orderNumber("orderNumber").orderDetails(orderDetails).build();

        String actual = target.create(order);

        assertThat(actual).isEqualTo(order.getOrderNumber());
    }

}