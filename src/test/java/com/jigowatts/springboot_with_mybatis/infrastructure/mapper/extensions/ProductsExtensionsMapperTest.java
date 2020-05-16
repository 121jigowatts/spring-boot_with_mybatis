package com.jigowatts.springboot_with_mybatis.infrastructure.mapper.extensions;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Optional;

import com.jigowatts.springboot_with_mybatis.domain.model.product.Product;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;

@MybatisTest
public class ProductsExtensionsMapperTest {
    @Autowired
    ProductsExtensionsMapper target;

    @Test
    @DisplayName("findById")
    public void findByIdTest() {
        String expectedProductId = "prd01";
        String expectedProductName = "productName01";

        Optional<Product> optProduct = target.findById(expectedProductId);

        optProduct.ifPresentOrElse(actual -> {
            assertThat(actual.getProductId()).isEqualTo(expectedProductId);
            assertThat(actual.getProductName()).isEqualTo(expectedProductName);
        }, () -> fail());
    }

    @Test
    @DisplayName("No Data")
    public void findByIdNoDataTest() {
        Optional<Product> optActual = target.findById("nodata");

        assertThat(optActual.isPresent()).isFalse();
    }
}