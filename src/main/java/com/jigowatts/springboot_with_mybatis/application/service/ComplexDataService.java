package com.jigowatts.springboot_with_mybatis.application.service;

import com.jigowatts.springboot_with_mybatis.application.component.AddressComponent;
import com.jigowatts.springboot_with_mybatis.application.component.CustomerComponent;
import com.jigowatts.springboot_with_mybatis.application.component.ItemComponent;
import com.jigowatts.springboot_with_mybatis.domain.model.complexdata.ComplexData;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * ComplexDataService
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ComplexDataService {
    private final AddressComponent addressComponent;
    private final CustomerComponent customerComponent;
    private final ItemComponent itemComponent;

    public ComplexData findById(String id) {
        log.info("[Params]ID: {}", id);
        return ComplexData.builder().id(id).address(addressComponent.getBy(id)).customers(customerComponent.getBy(id))
                .items(itemComponent.getBy(id)).build();
    }
}