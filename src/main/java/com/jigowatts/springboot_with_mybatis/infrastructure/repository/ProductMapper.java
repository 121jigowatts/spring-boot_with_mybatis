package com.jigowatts.springboot_with_mybatis.infrastructure.repository;

import java.util.Optional;

import com.jigowatts.springboot_with_mybatis.domain.model.product.Product;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductMapper {
    Optional<Product> findById(String productId);
}