package com.jigowatts.springboot_with_mybatis.repository;

import com.jigowatts.springboot_with_mybatis.domain.model.Message;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

/**
 * MessageRepositoryTest
 */
@MybatisTest
public class MessageRepositoryTest {

    @Autowired
    private MessageRepository messageRepository;

    @Test
    public void findOneTest() {
        Message expected = new Message();
        expected.setId(1);
        expected.setText("hello");
        Message actual = messageRepository.findOne(1);

        assertThat(actual.getId()).isEqualTo(expected.getId());
        assertThat(actual.getText()).isEqualTo(expected.getText());
    }

    @Test
    public void countTest() {
        long expected = 1L;
        long actual = messageRepository.count();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void findAllTest() {
        List<Message> expected = new ArrayList<Message>();

        Message message = new Message();
        message.setId(1);
        message.setText("hello");
        expected.add(message);

        List<Message> actual = messageRepository.findAll();
        assertThat(actual.size()).isEqualTo(expected.size());
    }

}