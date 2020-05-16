package com.jigowatts.springboot_with_mybatis.util.converter;

import static org.mockito.Mockito.doReturn;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import com.jigowatts.springboot_with_mybatis.domain.model.order.Order;
import com.jigowatts.springboot_with_mybatis.domain.model.order.OrderDetail;
import com.jigowatts.springboot_with_mybatis.domain.model.order.OrderFactory;
import com.jigowatts.springboot_with_mybatis.domain.model.product.Product;
import com.jigowatts.springboot_with_mybatis.presentation.resource.OrderDetailResource;
import com.jigowatts.springboot_with_mybatis.presentation.resource.OrderResource;
import com.jigowatts.springboot_with_mybatis.presentation.resource.ProductResource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OrderResourceConverterTest {
    @Mock
    OrderDetailResourceConverter orderDetailResourceConverter;
    @Mock
    OrderFactory orderFactory;

    @InjectMocks
    private OrderResourceConverter target;

    @BeforeEach
    public void setup() {

    }

    @Test
    @DisplayName("Nullの場合のエンティティ変換")
    public void toEntityNullTest() {
        Order actual = target.toEntity(null);
        assertThat(actual).isNull();
    }

    @Test
    @DisplayName("エンティティ変換")
    public void toEntityTest() {
        OrderDetailResource orderDetailResource01 = OrderDetailResource.builder().orderDetailId("01").build();
        OrderDetailResource orderDetailResource02 = OrderDetailResource.builder().orderDetailId("02").build();
        List<OrderDetailResource> orderDetailsResource = List.of(orderDetailResource01, orderDetailResource02);

        OrderResource orderResource = OrderResource.builder().orderNumber("orderNumber")
                .orderDetails(orderDetailsResource).build();

        OrderDetail orderDetail01 = OrderDetail.builder().orderDetailId("01").build();
        OrderDetail orderDetail02 = OrderDetail.builder().orderDetailId("02").build();
        List<OrderDetail> orderDetails = List.of(orderDetail01, orderDetail02);

        Order order = Order.builder().orderNumber("orderNumber").orderDetails(orderDetails).build();
        doReturn(orderDetails).when(orderDetailResourceConverter).toEntityList(orderDetailsResource);

        Order actual = target.toEntity(orderResource);

        assertThat(actual.getOrderNumber()).isEqualTo(order.getOrderNumber());
        OrderDetail actualOrderDetail01 = actual.getOrderDetails().get(0);
        assertThat(actualOrderDetail01.getOrderDetailId()).isEqualTo(orderDetail01.getOrderDetailId());

        OrderDetail actualOrderDetail02 = actual.getOrderDetails().get(1);
        assertThat(actualOrderDetail02.getOrderDetailId()).isEqualTo(orderDetail02.getOrderDetailId());
    }

    @Test
    @DisplayName("新規Orderのエンティティ変換")
    public void toEntityNewTest() {
        OrderDetailResource orderDetailResource01 = OrderDetailResource.builder().orderDetailId("01").build();
        OrderDetailResource orderDetailResource02 = OrderDetailResource.builder().orderDetailId("02").build();
        List<OrderDetailResource> orderDetailsResource = List.of(orderDetailResource01, orderDetailResource02);

        OrderDetail orderDetail01 = OrderDetail.builder().orderDetailId("01").build();
        OrderDetail orderDetail02 = OrderDetail.builder().orderDetailId("02").build();
        List<OrderDetail> orderDetails = List.of(orderDetail01, orderDetail02);

        Order order = Order.builder().orderNumber("orderNumber").orderDetails(orderDetails).build();
        doReturn(orderDetails).when(orderDetailResourceConverter).toEntityList(orderDetailsResource);
        doReturn(order).when(orderFactory).create(orderDetails);

        OrderResource resource = OrderResource.builder().orderDetails(orderDetailsResource).build();
        Order actual = target.toEntity(resource);

        assertThat(actual.getOrderNumber()).isEqualTo(order.getOrderNumber());
        OrderDetail actualOrderDetail01 = actual.getOrderDetails().get(0);
        assertThat(actualOrderDetail01.getOrderDetailId()).isEqualTo(orderDetail01.getOrderDetailId());

        OrderDetail actualOrderDetail02 = actual.getOrderDetails().get(1);
        assertThat(actualOrderDetail02.getOrderDetailId()).isEqualTo(orderDetail02.getOrderDetailId());
    }

    @Test
    @DisplayName("Nullの場合のリソース変換")
    public void toResourceNullTest() {
        OrderResource actual = target.toResource(null);
        assertThat(actual).isNull();
    }

    @Test
    @DisplayName("リソース変換")
    public void toResourceTest() {
        List<OrderDetail> orderDetails = generateOrderDetails();
        Order entity = Order.builder().orderNumber("orderNumber").orderDetails(orderDetails).build();

        List<OrderDetailResource> orderDetailsResource = generateOrderDetailsResource();
        doReturn(orderDetailsResource).when(orderDetailResourceConverter).toResourceList(orderDetails);

        OrderResource expected = OrderResource.builder().orderNumber("orderNumber").orderDetails(orderDetailsResource)
                .build();

        OrderResource actual = target.toResource(entity);

        assertThat(actual.getOrderNumber()).isEqualTo(expected.getOrderNumber());
        List<OrderDetailResource> actualOrderDetails = actual.getOrderDetails();

        assertThat(actualOrderDetails.size()).isEqualTo(2);

        OrderDetailResource odRes01 = actualOrderDetails.get(0);
        assertThat(odRes01.getOrderDetailId()).isEqualTo("orderDetailId001");
        assertThat(odRes01.getProduct().getProductId()).isEqualTo("productId001");
        assertThat(odRes01.getProduct().getProductName()).isEqualTo("productName001");

        OrderDetailResource odRes02 = actualOrderDetails.get(1);
        assertThat(odRes02.getOrderDetailId()).isEqualTo("orderDetailId002");
        assertThat(odRes02.getProduct().getProductId()).isEqualTo("productId002");
        assertThat(odRes02.getProduct().getProductName()).isEqualTo("productName002");

    }

    private List<OrderDetail> generateOrderDetails() {
        List<OrderDetail> orderDetails = List.of(OrderDetail.builder().orderDetailId("orderDetailId001")
                .product(Product.builder().productId("productId001").productName("productName001").build()).build(),
                OrderDetail.builder().orderDetailId("orderDetailId002")
                        .product(Product.builder().productId("productId002").productName("productName002").build())
                        .build());
        return orderDetails;
    }

    private List<OrderDetailResource> generateOrderDetailsResource() {
        List<OrderDetailResource> orderDetailsResource = List.of(
                OrderDetailResource.builder().orderDetailId("orderDetailId001")
                        .product(ProductResource.builder().productId("productId001").productName("productName001")
                                .build())
                        .build(),
                OrderDetailResource.builder().orderDetailId("orderDetailId002").product(
                        ProductResource.builder().productId("productId002").productName("productName002").build())
                        .build());
        return orderDetailsResource;
    }

}