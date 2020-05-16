package com.jigowatts.springboot_with_mybatis.util.converter;

import com.jigowatts.springboot_with_mybatis.domain.model.product.Product;
import com.jigowatts.springboot_with_mybatis.domain.model.product.ProductFactory;
import com.jigowatts.springboot_with_mybatis.presentation.resource.ProductResource;
import com.jigowatts.springboot_with_mybatis.presentation.resource.converter.ResourceConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductResourceConverter implements ResourceConverter<ProductResource, Product> {

    @Autowired
    ProductFactory productFactory;

    @Override
    public Product toEntity(ProductResource resource) {
        if (resource == null) {
            return null;
        }

        String productId = resource.getProductId();
        String productName = resource.getProductName();

        if (productId == null ) {
            return productFactory.create(productName);
        }

        return Product.builder()
        .productId(productId)
        .productName(productName)
        .build();
    }

    @Override
    public ProductResource toResource(Product entity) {
        if (entity == null) {
            return null;
        }

        return ProductResource.builder()
        .productId(entity.getProductId())
        .productName(entity.getProductName())
        .build();
    }

}