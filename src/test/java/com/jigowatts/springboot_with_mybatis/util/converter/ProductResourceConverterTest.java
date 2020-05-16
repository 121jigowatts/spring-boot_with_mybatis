package com.jigowatts.springboot_with_mybatis.util.converter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

import com.jigowatts.springboot_with_mybatis.domain.model.product.Product;
import com.jigowatts.springboot_with_mybatis.domain.model.product.ProductFactory;
import com.jigowatts.springboot_with_mybatis.presentation.resource.ProductResource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductResourceConverterTest {

    @Mock
    ProductFactory productFactory;
    @InjectMocks
    ProductResourceConverter target;

    @BeforeEach
    public void setup() {
    }

    @Test
    @DisplayName("Nullの場合のエンティティ変換")
    public void toEntityNullTest() {
        Product actual = target.toEntity(null);
        assertThat(actual).isNull();
    }

    @Test
    @DisplayName("エンティティ変換")
    public void toEntityTest() {
        ProductResource expected = ProductResource.builder()
        .productId("productId")
        .productName("productName")
        .build();

        Product actual = target.toEntity(expected);

        assertThat(actual.getProductId()).isEqualTo(expected.getProductId());
        assertThat(actual.getProductName()).isEqualTo(expected.getProductName());
    }

    @Test
    @DisplayName("新規Productのエンティティ変換")
    public void toEntityNewTest() {
        ProductResource resource = ProductResource.builder()
        .productName("productName")
        .build();
        Product expected = Product.builder()
        .productId("productId")
        .productName("productName")
        .build();
        doReturn(expected).when(productFactory).create("productName");
        Product actual = target.toEntity(resource);

        assertThat(actual.getProductId()).isEqualTo(expected.getProductId());
        assertThat(actual.getProductName()).isEqualTo(expected.getProductName());
    }

    @Test
    @DisplayName("Nullの場合のリソース変換")
    public void toResourceNullTest() {
        ProductResource actual = target.toResource(null);
        assertThat(actual).isNull();
    }

    @Test
    @DisplayName("リソース変換")
    public void toResourceTest() {
        ProductResource expected = ProductResource.builder().productId("productId").productName("productName").build();

        Product entity = Product.builder().productId("productId").productName("productName").build();

        ProductResource actual = target.toResource(entity);

        assertThat(actual.getProductId()).isEqualTo(expected.getProductId());
        assertThat(actual.getProductName()).isEqualTo(expected.getProductName());
    }
}