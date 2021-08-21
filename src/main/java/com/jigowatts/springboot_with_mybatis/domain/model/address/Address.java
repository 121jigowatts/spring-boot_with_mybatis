package com.jigowatts.springboot_with_mybatis.domain.model.address;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private String id;
    private String zip;
    private String street;
    private String city;
    private String country;
}
