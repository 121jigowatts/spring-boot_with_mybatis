package com.jigowatts.springboot_with_mybatis.infrastructure.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

import java.util.List;

import com.jigowatts.springboot_with_mybatis.domain.model.order.Order;
import com.jigowatts.springboot_with_mybatis.domain.model.order.OrderDetail;
import com.jigowatts.springboot_with_mybatis.domain.model.product.Product;
import com.jigowatts.springboot_with_mybatis.infrastructure.entity.OrderDetails;
import com.jigowatts.springboot_with_mybatis.infrastructure.entity.OrdersKey;
import com.jigowatts.springboot_with_mybatis.infrastructure.mapper.OrderDetailsMapper;
import com.jigowatts.springboot_with_mybatis.infrastructure.mapper.OrdersMapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OrderRepositoryImplTest {
    @InjectMocks
    private OrderRepositoryImpl target;
    @Mock
    private OrdersMapper ordersMapper;
    @Mock
    private OrderDetailsMapper orderDetailsMapper;

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