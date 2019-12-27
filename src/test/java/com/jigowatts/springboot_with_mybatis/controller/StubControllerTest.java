package com.jigowatts.springboot_with_mybatis.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * StubControllerTest
 */
@SpringBootTest
@AutoConfigureMockMvc
public class StubControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void globalErrorTest() throws Exception {
        
        mockMvc.perform(MockMvcRequestBuilders.get("/mock/global-error"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status()
                .is(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("System error is occurred."))
                .andExpect(MockMvcResultMatchers.jsonPath("$.documentation_url").value("http://example.com/api/errors"));

    }

    @Test
    public void notFoundTest() throws Exception {
        
        mockMvc.perform(MockMvcRequestBuilders.get("/mock/not-found"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status()
                .is(HttpStatus.NOT_FOUND.value()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Resource is not found."))
                .andExpect(MockMvcResultMatchers.jsonPath("$.documentation_url").value("http://example.com/api/errors"));

    }
}