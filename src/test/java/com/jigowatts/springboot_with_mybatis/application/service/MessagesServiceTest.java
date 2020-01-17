package com.jigowatts.springboot_with_mybatis.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import com.jigowatts.springboot_with_mybatis.domain.model.message.Message;
import com.jigowatts.springboot_with_mybatis.domain.model.message.MessageCriteria;
import com.jigowatts.springboot_with_mybatis.infrastructure.repository.MessageImplRepository;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * MessagesServiceTest
 */
@SpringBootTest
public class MessagesServiceTest {

    @Mock
    MessageImplRepository messageRepository;

    @InjectMocks
    MessagesService messagesService = new MessagesService();

    @Test
    public void findAllByCriteriaTest() {
        List<Message> expected = new ArrayList<Message>();
        // #1
        Message resource01 = new Message();
        resource01.setId(1);
        resource01.setText("foo");
        expected.add(resource01);
        // #2
        Message resource02 = new Message();
        resource02.setId(2);
        resource02.setText("bar");
        expected.add(resource02);

        MessageCriteria criteria = new MessageCriteria();

        doReturn(expected).when(messageRepository).findAllByCriteria(criteria);

        List<Message> actual = messagesService.findAllByCriteria(criteria);
        assertEquals(expected.size(), actual.size());
        assertEquals(expected.get(0).getId(), actual.get(0).getId());
        assertEquals(expected.get(0).getText(), actual.get(0).getText());

        assertEquals(expected.get(1).getId(), actual.get(1).getId());
        assertEquals(expected.get(1).getText(), actual.get(1).getText());
    }

    @Test
    public void findByIdTest() {
        Message expected = new Message();
        expected.setId(1);
        expected.setText("hoge");
        doReturn(expected).when(messageRepository).findOne(anyInt());

        Message actual = messagesService.findById(1);
        assertEquals(expected, actual);
    }

    @Test
    public void countTest() {
        long expected = 5;
        doReturn(expected).when(messageRepository).count();

        long actual = messagesService.count();
        assertEquals(expected, actual);
    }

    @Test
    public void createTest() {
        Message newMessage = new Message();
        newMessage.setId(0);
        newMessage.setText("hoge");

        doNothing().when(messageRepository).create(newMessage);

        messagesService.create(newMessage);
        verify(messageRepository, times(1)).create(newMessage);
    }

    @Test
    public void updateTest() {
        Message expected = new Message();
        doReturn(true).when(messageRepository).update(expected);

        Message actual = messagesService.update(expected);
        assertEquals(expected, actual);
    }

    @Test
    public void deleteTest() {
        boolean expected = true;
        doReturn(expected).when(messageRepository).delete(anyInt());
        boolean actual = messagesService.delete(1);
        assertEquals(expected, actual);
    }
}