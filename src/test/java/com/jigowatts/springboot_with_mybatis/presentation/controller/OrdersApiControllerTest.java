package com.jigowatts.springboot_with_mybatis.presentation.controller;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jigowatts.springboot_with_mybatis.aop.ApiExceptionHandler;
import com.jigowatts.springboot_with_mybatis.application.service.OrdersService;
import com.jigowatts.springboot_with_mybatis.domain.model.order.Order;
import com.jigowatts.springboot_with_mybatis.presentation.resource.OrderDetailResource;
import com.jigowatts.springboot_with_mybatis.presentation.resource.OrderResource;
import com.jigowatts.springboot_with_mybatis.presentation.resource.ProductResource;
import com.jigowatts.springboot_with_mybatis.util.converter.OrderResourceConverter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest
public class OrdersApiControllerTest {
    MockMvc mockMvc;
    ObjectMapper mapper;

    @Mock
    OrdersService ordersService;

    @Mock
    OrderResourceConverter orderResourceConverter;

    @InjectMocks
    OrdersApiController target;

    @BeforeEach
    public void setupMockMvc() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(target).setControllerAdvice(new ApiExceptionHandler()).build();
        mapper = new ObjectMapper();
    }

    @Test
    @DisplayName("Order１件取得")
    public void getOrderByOrderNumberTest() {
        Order order = Order.builder().build();
        Optional<Order> optOrder = Optional.ofNullable(order);
        doReturn(optOrder).when(ordersService).findByOrderNumber("orderNumber");
        doReturn(OrderResource.builder().build()).when(orderResourceConverter).toResource(order);
        MockHttpServletRequestBuilder getRequest = MockMvcRequestBuilders.get("/orders/orderNumber");

        try {
            mockMvc.perform(getRequest).andDo(print()).andExpect(status().isOk()).andReturn();
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @DisplayName("404")
    public void notFoundTest() throws Exception {
        doReturn(Optional.empty()).when(ordersService).findByOrderNumber(anyString());
        MockHttpServletRequestBuilder getRequest = MockMvcRequestBuilders.get("/orders/nocontent");

        mockMvc.perform(getRequest).andDo(print()).andExpect(status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Order is not found.")).andReturn();
    }

    @Test
    @DisplayName("Order１件保存")
    public void postOrderTest() throws JsonProcessingException, Exception {
        OrderDetailResource orderDetailResouce = OrderDetailResource.builder().quantity(1).unitPrice(100)
                .product(ProductResource.builder().productId("productId").build()).build();
        List<OrderDetailResource> orderDetailsResource = List.of(orderDetailResouce);
        OrderResource request = OrderResource.builder().orderNumber("orderNumber").orderDetails(orderDetailsResource)
                .build();
        Order newOrder = Order.builder().build();
        doReturn(newOrder).when(orderResourceConverter).toEntity(request);
        doReturn("orderNumber").when(ordersService).create(newOrder);

        mockMvc.perform(MockMvcRequestBuilders.post("/orders").content(mapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isCreated()).andReturn();
    }
}