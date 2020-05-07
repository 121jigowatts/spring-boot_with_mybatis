package com.jigowatts.springboot_with_mybatis.presentation.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import java.util.ArrayList;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jigowatts.springboot_with_mybatis.domain.model.message.Message;
import com.jigowatts.springboot_with_mybatis.domain.model.message.MessageCriteria;
import com.jigowatts.springboot_with_mybatis.presentation.resource.MessageResource;
import com.jigowatts.springboot_with_mybatis.util.converter.MessageResourceConverter;
import com.jigowatts.springboot_with_mybatis.aop.ApiExceptionHandler;
import com.jigowatts.springboot_with_mybatis.application.service.MessagesService;

import org.junit.jupiter.api.BeforeEach;
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

/**
 * MessagesControllerTest
 */
@SpringBootTest
public class MessagesControllerTest {

    MockMvc mockMvc;
    ObjectMapper mapper;

    @Mock
    MessagesService messagesService;

    @Mock
    MessageResourceConverter resourceConverter;

    @InjectMocks
    MessagesController controller;

    @BeforeEach
    public void setupMockMvc() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).setControllerAdvice(new ApiExceptionHandler())
                .build();
        mapper = new ObjectMapper();
    }

    @Test
    public void searchMessagesTest() throws Exception {
        MessageCriteria criteria = MessageCriteria.builder().build();
        doReturn(new ArrayList<MessageResource>()).when(messagesService).findAllByCriteria(criteria);

        MockHttpServletRequestBuilder getRequest = MockMvcRequestBuilders.get("/messages");

        mockMvc.perform(getRequest).andDo(print()).andExpect(status().isOk()).andReturn();
    }

    @Test
    public void getMessageByIdTest() throws Exception {
        doReturn(Optional.ofNullable(Message.builder().build())).when(messagesService).findById(anyInt());

        MockHttpServletRequestBuilder getRequest = MockMvcRequestBuilders.get("/messages/1");

        mockMvc.perform(getRequest).andDo(print()).andExpect(status().isOk()).andReturn();
    }

    @Test
    public void getMessageByIdNoDataTest() throws Exception {
        doReturn(Optional.empty()).when(messagesService).findById(anyInt());

        MockHttpServletRequestBuilder getRequest = MockMvcRequestBuilders.get("/messages/0");

        mockMvc.perform(getRequest).andDo(print()).andExpect(status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Message is not found.")).andReturn();
    }

    @Test
    public void getMessageCountTest() throws Exception {
        doReturn(1L).when(messagesService).count();

        MockHttpServletRequestBuilder getRequest = MockMvcRequestBuilders.get("/messages/count");

        mockMvc.perform(getRequest).andDo(print()).andExpect(status().isOk()).andReturn();
    }

    @Test
    public void postMessagesTest() throws JsonProcessingException, Exception {
        MessageResource request = MessageResource.builder().build();
        Message newMessage = Message.builder().text("hoge").build();
        doReturn(newMessage).when(resourceConverter).toEntity(any());

        mockMvc.perform(MockMvcRequestBuilders.post("/messages").content(mapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isCreated()).andReturn();
    }

    @Test
    public void putMessagesTest() throws Exception {
        MessageResource request = MessageResource.builder().id(1).text("foo").build();

        mockMvc.perform(MockMvcRequestBuilders.put("/messages").content(mapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isNoContent()).andReturn();
    }

    @Test
    public void deleteMessagesTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/messages/1")).andDo(print()).andExpect(status().isNoContent())
                .andReturn();
    }
}