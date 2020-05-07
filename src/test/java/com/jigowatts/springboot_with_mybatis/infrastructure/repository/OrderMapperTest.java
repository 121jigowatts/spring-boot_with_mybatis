package com.jigowatts.springboot_with_mybatis.infrastructure.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;
import java.util.Optional;

import com.jigowatts.springboot_with_mybatis.domain.model.order.Order;
import com.jigowatts.springboot_with_mybatis.domain.model.order.OrderDetail;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;

@MybatisTest
public class OrderMapperTest {
    @Autowired
    OrderMapper target;

    @Test
    @DisplayName("findById")
    public void findByOrderNumberTest() {
        String expectedOrderNumber = "oder001";
        String expectedOrderDetailId = "detail001";
        int expectedQuantity = 1;
        int expectedUnitPrice = 100;
        String expectedProductId = "prd01";
        String expectedProductName = "productName01";

        Optional<Order> optActual = target.findByOrderNumber(expectedOrderNumber);

        optActual.ifPresentOrElse(actual -> {
            assertThat(actual.getOrderNumber()).isEqualTo(expectedOrderNumber);
            List<OrderDetail> actualOrderDetails = actual.getOrderDetails();

            assertThat(actualOrderDetails.size()).isEqualTo(2);

            OrderDetail actualOrderDetail = actualOrderDetails.get(0);
            assertThat(actualOrderDetail.getOrderDetailId()).isEqualTo(expectedOrderDetailId);
            assertThat(actualOrderDetail.getQuantity()).isEqualTo(expectedQuantity);
            assertThat(actualOrderDetail.getUnitPrice()).isEqualTo(expectedUnitPrice);
            assertThat(actualOrderDetail.getProduct().getProductId()).isEqualTo(expectedProductId);
            assertThat(actualOrderDetail.getProduct().getProductName()).isEqualTo(expectedProductName);
        }, () -> fail());

    }

    @Test
    @DisplayName("No Data")
    public void findByOrderNumberNoDataTest() {
        Optional<Order> optActual = target.findByOrderNumber("nodata");

        assertThat(optActual.isPresent()).isFalse();
    }
}