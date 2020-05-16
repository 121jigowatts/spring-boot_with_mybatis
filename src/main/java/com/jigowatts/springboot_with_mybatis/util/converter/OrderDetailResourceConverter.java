package com.jigowatts.springboot_with_mybatis.util.converter;

import java.util.List;
import java.util.stream.Collectors;

import com.jigowatts.springboot_with_mybatis.domain.model.order.OrderDetail;
import com.jigowatts.springboot_with_mybatis.domain.model.order.OrderDetailFactory;
import com.jigowatts.springboot_with_mybatis.domain.model.product.Product;
import com.jigowatts.springboot_with_mybatis.presentation.resource.OrderDetailResource;
import com.jigowatts.springboot_with_mybatis.presentation.resource.ProductResource;
import com.jigowatts.springboot_with_mybatis.presentation.resource.converter.ResourceConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderDetailResourceConverter implements ResourceConverter<OrderDetailResource, OrderDetail> {

    @Autowired
    ProductResourceConverter productResourceConverter;
    @Autowired
    OrderDetailFactory orderDetailFactory;

    @Override
    public OrderDetail toEntity(OrderDetailResource resource) {
        if (resource == null) {
            return null;
        }

        int quantity = resource.getQuantity();
        int unitPrice = resource.getUnitPrice();
        Product product = productResourceConverter.toEntity(resource.getProduct());

        if (resource.getOrderDetailId() == null) {
            return orderDetailFactory.create(quantity, unitPrice, product);
        }

        return OrderDetail.builder().orderDetailId(resource.getOrderDetailId()).quantity(quantity).unitPrice(unitPrice)
                .product(product).build();
    }

    public List<OrderDetail> toEntityList(List<OrderDetailResource> resources) {
        return resources.stream().map(this::toEntity).collect(Collectors.toList());
    }

    @Override
    public OrderDetailResource toResource(OrderDetail entity) {
        if (entity == null) {
            return null;
        }

        ProductResource product = productResourceConverter.toResource(entity.getProduct());

        return OrderDetailResource.builder().orderDetailId(entity.getOrderDetailId()).quantity(entity.getQuantity())
                .unitPrice(entity.getUnitPrice()).product(product).build();
    }

    public List<OrderDetailResource> toResourceList(List<OrderDetail> entities) {
        return entities.stream().map(this::toResource).collect(Collectors.toList());
    }

}