package com.jigowatts.springboot_with_mybatis.presentation.controller;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jigowatts.springboot_with_mybatis.domain.model.Message;
import com.jigowatts.springboot_with_mybatis.domain.model.MessageCriteria;
import com.jigowatts.springboot_with_mybatis.presentation.resource.MessageResource;
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

    @InjectMocks
    MessagesController controller;

    @BeforeEach
    public void setupMockMvc() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        mapper = new ObjectMapper();
    }

    @Test
    public void searchMessagesTest() throws Exception {
        MessageCriteria criteria = new MessageCriteria();
        doReturn(new ArrayList<MessageResource>()).when(messagesService).findAllByCriteria(criteria);

        MockHttpServletRequestBuilder getRequest = MockMvcRequestBuilders.get("/messages");

        mockMvc.perform(getRequest).andDo(print()).andExpect(status().isOk()).andReturn();
    }

    @Test
    public void getMessageByIdTest() throws Exception {
        doReturn(new Message()).when(messagesService).findById(anyInt());

        MockHttpServletRequestBuilder getRequest = MockMvcRequestBuilders.get("/messages/1");

        mockMvc.perform(getRequest).andDo(print()).andExpect(status().isOk()).andReturn();
    }

    @Test
    public void getMessageCountTest() throws Exception {
        doReturn(1L).when(messagesService).count();

        MockHttpServletRequestBuilder getRequest = MockMvcRequestBuilders.get("/messages/count");

        mockMvc.perform(getRequest).andDo(print()).andExpect(status().isOk()).andReturn();
    }

    @Test
    public void postMessagesTest() throws JsonProcessingException, Exception {
        MessageResource request = new MessageResource();
        request.setText("hoge");

        mockMvc.perform(MockMvcRequestBuilders.post("/messages").content(mapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isCreated()).andReturn();
    }

    @Test
    public void putMessagesTest() throws Exception {
        MessageResource request = new MessageResource();
        request.setId(1);
        request.setText("foo");

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