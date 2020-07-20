package com.jigowatts.springboot_with_mybatis.presentation.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
//import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.*;
//import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jigowatts.springboot_with_mybatis.presentation.resource.MessageResource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
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
                this.mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
                mapper = new ObjectMapper();
        }

        @WithMockUser(username = "user", roles = "USER")
        @Test
        public void globalErrorTest() throws Exception {

                mockMvc.perform(MockMvcRequestBuilders.get("/mock/global-error").header("X-API-Key", "1234-5678-90ab"))
                                .andDo(print())
                                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                                .andExpect(authenticated())
                                .andExpect(MockMvcResultMatchers.jsonPath("$.message")
                                                .value("System error is occurred."))
                                .andExpect(MockMvcResultMatchers.jsonPath("$.documentation_url")
                                                .value("http://example.com/api/errors"));

        }

        @WithMockUser(username = "user", roles = "USER")
        @Test
        public void notFoundTest() throws Exception {

                mockMvc.perform(MockMvcRequestBuilders.get("/mock/not-found").header("X-API-Key", "1234-5678-90ab"))
                                .andDo(print())
                                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.NOT_FOUND.value()))
                                .andExpect(authenticated())
                                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Resource is not found."))
                                .andExpect(MockMvcResultMatchers.jsonPath("$.documentation_url")
                                                .value("http://example.com/api/errors"));

        }

        @WithMockUser(username = "user", roles = "USER")
        @Test
        public void validationErrorTest() throws JsonProcessingException, Exception {
                MessageResource request = MessageResource.builder().text("12345678901").build();

                mockMvc.perform(MockMvcRequestBuilders.post("/messages").content(mapper.writeValueAsString(request))
                                .header("X-API-Key", "1234-5678-90ab").contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)).andDo(print())
                                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.BAD_REQUEST.value()))
                                .andExpect(authenticated())
                                .andExpect(MockMvcResultMatchers.jsonPath("$.message")
                                                .value("Request value is invalid."))
                                .andExpect(MockMvcResultMatchers.jsonPath("$.documentation_url")
                                                .value("http://example.com/api/errors"));
        }

        @Test
        public void authenticationFailedTest() throws Exception {
                mockMvc.perform(MockMvcRequestBuilders.get("/mock/not-found").header("X-API-Key", "bad-key"))
                                .andDo(print())
                                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.FORBIDDEN.value()));
        }
}