package com.jigowatts.springboot_with_mybatis.domain.model.complexdata;

import java.util.List;

import com.jigowatts.springboot_with_mybatis.domain.model.address.Address;
import com.jigowatts.springboot_with_mybatis.domain.model.customer.Customer;
import com.jigowatts.springboot_with_mybatis.domain.model.item.Item;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ComplexData {
    private String id;
    private Address address;
    private List<Customer> customers;
    private List<Item> items;
}