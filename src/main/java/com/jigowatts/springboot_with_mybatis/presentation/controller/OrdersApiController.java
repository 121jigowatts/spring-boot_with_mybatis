package com.jigowatts.springboot_with_mybatis.presentation.controller;

import java.net.URI;

import javax.validation.Valid;

import com.jigowatts.springboot_with_mybatis.application.service.OrdersService;
import com.jigowatts.springboot_with_mybatis.domain.model.order.Order;
import com.jigowatts.springboot_with_mybatis.presentation.resource.OrderResource;
import com.jigowatts.springboot_with_mybatis.util.converter.OrderResourceConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("orders")
public class OrdersApiController {
    @Autowired
    OrdersService ordersService;
    @Autowired
    OrderResourceConverter orderResourceConverter;

    @GetMapping(value = "/{orderNumber}")
    public OrderResource getOrderByOrderNumber(@PathVariable final String orderNumber) {
        Order order = ordersService.findByOrderNumber(orderNumber)
                .orElseThrow(() -> new NotFoundException("Order is not found."));

        return orderResourceConverter.toResource(order);
    }

    @PostMapping
    public ResponseEntity<OrderResource> postOrder(@Valid @RequestBody final OrderResource newOrderResource,
            UriComponentsBuilder uriBuilder) {
        Order newOrder = orderResourceConverter.toEntity(newOrderResource);
        String orderNumber = ordersService.create(newOrder);

        URI resourceUri = uriBuilder.path("orders/{orderNumber}").buildAndExpand(orderNumber).encode()
                .toUri();

        return ResponseEntity.created(resourceUri).build();
    }
}