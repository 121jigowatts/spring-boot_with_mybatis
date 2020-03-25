package com.jigowatts.springboot_with_mybatis.presentation.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jigowatts.springboot_with_mybatis.application.service.UsersService;
import com.jigowatts.springboot_with_mybatis.domain.model.user.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * UsersControllerTest
 */
@SpringBootTest
public class UsersControllerTest {

    MockMvc mockMvc;
    ObjectMapper mapper;
    @Mock
    UsersService usersService;

    @InjectMocks
    UsersController controller;

    @BeforeEach
    public void setupMockMvc() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        mapper = new ObjectMapper();
    }

    @Test
    public void getUserByIdTest() throws Exception {
        doReturn(new User()).when(usersService).findById(anyString());
        
        MockHttpServletRequestBuilder getRequest = MockMvcRequestBuilders.get("/users/id");

        mockMvc.perform(getRequest).andDo(print()).andExpect(status().isOk()).andReturn();
    }
}