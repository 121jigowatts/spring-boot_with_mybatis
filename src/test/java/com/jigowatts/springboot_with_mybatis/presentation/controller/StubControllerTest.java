package com.jigowatts.springboot_with_mybatis.presentation.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jigowatts.springboot_with_mybatis.auth.ApiKeyAuthFilter;
import com.jigowatts.springboot_with_mybatis.auth.ApiKeyAuthManager;
import com.jigowatts.springboot_with_mybatis.presentation.resource.MessageResource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * StubControllerTest
 */
@SpringBootTest
public class StubControllerTest {

    @Autowired
    WebApplicationContext context;

    MockMvc mockMvc;
    ObjectMapper mapper;

    @BeforeEach
    public void setupMockMvc() {
        AuthenticationManager authenticationManager = new ApiKeyAuthManager();
        AbstractPreAuthenticatedProcessingFilter apiKeyFilter = new ApiKeyAuthFilter();
        apiKeyFilter.setAuthenticationManager(authenticationManager);

        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).addFilter(apiKeyFilter).build();
        mapper = new ObjectMapper();
    }

    @Test
    public void globalErrorTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/mock/global-error")).andDo(print())
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("System error is occurred.")).andExpect(
                        MockMvcResultMatchers.jsonPath("$.documentation_url").value("http://example.com/api/errors"));

    }

    @Test
    public void notFoundTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/mock/not-found")).andDo(print())
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.NOT_FOUND.value()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Resource is not found.")).andExpect(
                        MockMvcResultMatchers.jsonPath("$.documentation_url").value("http://example.com/api/errors"));

    }

    @Test
    public void validationErrorTest() throws JsonProcessingException, Exception {
        MessageResource request = MessageResource.builder().text("12345678901").build();

        mockMvc.perform(MockMvcRequestBuilders.post("/messages").content(mapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.BAD_REQUEST.value()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Request value is invalid.")).andExpect(
                        MockMvcResultMatchers.jsonPath("$.documentation_url").value("http://example.com/api/errors"));
    }
}