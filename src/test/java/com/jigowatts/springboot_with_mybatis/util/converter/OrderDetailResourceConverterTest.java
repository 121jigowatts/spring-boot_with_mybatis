package com.jigowatts.springboot_with_mybatis.util.converter;

import static org.mockito.Mockito.doReturn;

import java.util.List;

import com.jigowatts.springboot_with_mybatis.domain.model.order.OrderDetail;
import com.jigowatts.springboot_with_mybatis.domain.model.order.OrderDetailFactory;
import com.jigowatts.springboot_with_mybatis.domain.model.product.Product;
import com.jigowatts.springboot_with_mybatis.presentation.resource.OrderDetailResource;
import com.jigowatts.springboot_with_mybatis.presentation.resource.ProductResource;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OrderDetailResourceConverterTest {
    @Mock
    ProductResourceConverter productResourceConverter;
    @Mock
    OrderDetailFactory orderDetailFactory;

    @InjectMocks
    OrderDetailResourceConverter target;

    private String orderDetailId01 = "001";
    private String orderDetailId02 = "002";
    private int quantity01 = 1;
    private int quantity02 = 2;
    private int unitPrice01 = 100;
    private int unitPrice02 = 200;
    private String productId01 = "prd001";
    private String productId02 = "prd002";
    private String productName01 = "productName001";
    private String productName02 = "productName002";

    private Product prd01;
    private Product prd02;
    private OrderDetail od01;
    private OrderDetail od02;
    private List<OrderDetail> orderDetails;
    private ProductResource prdRes01;
    private ProductResource prdRes02;
    private OrderDetailResource odRes01;
    private OrderDetailResource odRes02;
    private List<OrderDetailResource> orderDetailsResource;

    @BeforeEach
    public void setup() {
        prd01 = Product.builder().productId(productId01).productName(productName01).build();
        od01 = OrderDetail.builder().orderDetailId(orderDetailId01).quantity(quantity01).unitPrice(unitPrice01)
                .product(prd01).build();
        prd02 = Product.builder().productId(productId02).productName(productName02).build();
        od02 = OrderDetail.builder().orderDetailId(orderDetailId02).quantity(quantity02).unitPrice(unitPrice02)
                .product(prd02).build();
        orderDetails = List.of(od01, od02);
        prdRes01 = ProductResource.builder().productId(productId01).productName(productName01).build();
        prdRes02 = ProductResource.builder().productId(productId02).productName(productName02).build();

        odRes01 = OrderDetailResource.builder().orderDetailId(orderDetailId01).quantity(quantity01)
                .unitPrice(unitPrice01).product(prdRes01).build();
        odRes02 = OrderDetailResource.builder().orderDetailId(orderDetailId02).quantity(quantity02)
                .unitPrice(unitPrice02).product(prdRes02).build();
        orderDetailsResource = List.of(odRes01, odRes02);
    }

    @Test
    @DisplayName("Nullの場合のエンティティ変換")
    public void toEntityNullTest() {
        OrderDetail actual = target.toEntity(null);
        assertThat(actual).isNull();
    }

    @Test
    @DisplayName("空リストの場合のエンティティ変換")
    public void toEntityListEmptyTest() {
        List<OrderDetailResource> emptyList = List.of();
        List<OrderDetail> actual = target.toEntityList(emptyList);

        assertThat(actual.size()).isEqualTo(0);
    }

    @Test
    @DisplayName("エンティティ変換")
    public void toEntityTest() {
        ProductResource productResource = ProductResource.builder().productId("productId").productName("productName")
                .build();
        OrderDetailResource orderDetailResource = OrderDetailResource.builder().orderDetailId("orderDetailId")
                .quantity(1).unitPrice(100).product(productResource).build();

        Product expectedProduct = Product.builder().productId("productId").productName("productName").build();
        OrderDetail expected = OrderDetail.builder().orderDetailId("orderDetailId").quantity(1).unitPrice(100)
                .product(expectedProduct).build();

        doReturn(expectedProduct).when(productResourceConverter).toEntity(productResource);

        OrderDetail actual = target.toEntity(orderDetailResource);

        assertThat(actual.getOrderDetailId()).isEqualTo(expected.getOrderDetailId());
        assertThat(actual.getQuantity()).isEqualTo(expected.getQuantity());
        assertThat(actual.getUnitPrice()).isEqualTo(expected.getUnitPrice());
        assertThat(actual.getProduct().getProductId()).isEqualTo(expected.getProduct().getProductId());
        assertThat(actual.getProduct().getProductName()).isEqualTo(expected.getProduct().getProductName());
    }

    @Test
    @DisplayName("新規OrderDetailのエンティティ変換")
    public void toEntityNewTest() {
        ProductResource productResource = ProductResource.builder().productId("productId").productName("productName")
                .build();
        OrderDetailResource resource = OrderDetailResource.builder().quantity(1).unitPrice(100).product(productResource)
                .build();

        Product product = Product.builder().productId("productId").productName("productName").build();

        OrderDetail orderDetail = OrderDetail.builder().orderDetailId("orderDetailId").quantity(1).unitPrice(100)
                .product(product).build();

        doReturn(orderDetail).when(orderDetailFactory).create(1, 100, product);
        doReturn(product).when(productResourceConverter).toEntity(productResource);

        OrderDetail actual = target.toEntity(resource);

        assertThat(actual.getOrderDetailId()).isEqualTo(orderDetail.getOrderDetailId());
        assertThat(actual.getQuantity()).isEqualTo(orderDetail.getQuantity());
        assertThat(actual.getUnitPrice()).isEqualTo(orderDetail.getUnitPrice());
        assertThat(actual.getProduct().getProductId()).isEqualTo(orderDetail.getProduct().getProductId());
        assertThat(actual.getProduct().getProductName()).isEqualTo(orderDetail.getProduct().getProductName());
    }

    @Test
    @DisplayName("リストのエンティティ変換")
    public void toEntityListTest() {
        doReturn(prd01).when(productResourceConverter).toEntity(prdRes01);
        doReturn(prd02).when(productResourceConverter).toEntity(prdRes02);

        List<OrderDetail> actual = target.toEntityList(orderDetailsResource);

        assertThat(actual.size()).isEqualTo(2);

        OrderDetail actual01 = actual.get(0);
        assertThat(actual01.getOrderDetailId()).isEqualTo(orderDetailId01);
        assertThat(actual01.getQuantity()).isEqualTo(quantity01);
        assertThat(actual01.getUnitPrice()).isEqualTo(unitPrice01);
        Product actualProduct01 = actual01.getProduct();
        assertThat(actualProduct01.getProductId()).isEqualTo(productId01);
        assertThat(actualProduct01.getProductName()).isEqualTo(productName01);

        OrderDetail actual02 = actual.get(1);
        assertThat(actual02.getOrderDetailId()).isEqualTo(orderDetailId02);
        assertThat(actual02.getQuantity()).isEqualTo(quantity02);
        assertThat(actual02.getUnitPrice()).isEqualTo(unitPrice02);
        Product actualProduct02 = actual02.getProduct();
        assertThat(actualProduct02.getProductId()).isEqualTo(productId02);
        assertThat(actualProduct02.getProductName()).isEqualTo(productName02);
    }

    @Test
    @DisplayName("Nullの場合のリソース変換")
    public void toResourceNullTest() {
        OrderDetailResource actual = target.toResource(null);
        assertThat(actual).isNull();
    }

    @Test
    @DisplayName("空リストの場合のリソース変換")
    public void toResourceListEmptyTest() {
        List<OrderDetail> emptyList = List.of();
        List<OrderDetailResource> actual = target.toResourceList(emptyList);

        assertThat(actual.size()).isEqualTo(0);
    }

    @Test
    @DisplayName("リソース変換")
    public void toResourceTest() {
        ProductResource productResource = ProductResource.builder().productId("productId").productName("productName")
                .build();
        Product product = Product.builder().productId("productId").productName("productName").build();

        doReturn(productResource).when(productResourceConverter).toResource(product);

        OrderDetail expected = OrderDetail.builder().orderDetailId("orderDetailId").quantity(1).unitPrice(100)
                .product(product).build();

        OrderDetailResource actual = target.toResource(expected);
        ProductResource actualProduct = actual.getProduct();

        assertThat(actual.getOrderDetailId()).isEqualTo(expected.getOrderDetailId());
        assertThat(actual.getQuantity()).isEqualTo(expected.getQuantity());
        assertThat(actual.getUnitPrice()).isEqualTo(expected.getUnitPrice());
        assertThat(actualProduct.getProductId()).isEqualTo(product.getProductId());
        assertThat(actualProduct.getProductName()).isEqualTo(product.getProductName());
    }

    @Test
    @DisplayName("リストのリソース変換")
    public void toResourceListTest() {
        doReturn(prdRes01).when(productResourceConverter).toResource(prd01);
        doReturn(prdRes02).when(productResourceConverter).toResource(prd02);

        List<OrderDetailResource> actual = target.toResourceList(orderDetails);

        assertThat(actual.size()).isEqualTo(2);
        OrderDetailResource actual01 = actual.get(0);

        assertThat(actual01.getOrderDetailId()).isEqualTo(orderDetailId01);
        assertThat(actual01.getQuantity()).isEqualTo(quantity01);
        assertThat(actual01.getUnitPrice()).isEqualTo(unitPrice01);
        ProductResource actualProduct01 = actual01.getProduct();
        assertThat(actualProduct01.getProductId()).isEqualTo(productId01);
        assertThat(actualProduct01.getProductName()).isEqualTo(productName01);

        OrderDetailResource actual02 = actual.get(1);

        assertThat(actual02.getOrderDetailId()).isEqualTo(orderDetailId02);
        assertThat(actual02.getQuantity()).isEqualTo(quantity02);
        assertThat(actual02.getUnitPrice()).isEqualTo(unitPrice02);
        ProductResource actualProduct02 = actual02.getProduct();
        assertThat(actualProduct02.getProductId()).isEqualTo(productId02);
        assertThat(actualProduct02.getProductName()).isEqualTo(productName02);
    }

}