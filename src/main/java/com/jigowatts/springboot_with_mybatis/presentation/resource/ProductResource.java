package com.jigowatts.springboot_with_mybatis.presentation.resource;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResource {
    private String productId;
    private String productName;
}