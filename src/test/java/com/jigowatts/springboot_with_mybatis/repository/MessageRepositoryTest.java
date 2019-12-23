package com.jigowatts.springboot_with_mybatis.repository;

import com.jigowatts.springboot_with_mybatis.domain.model.Message;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

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
    @Sql(scripts = "/com/jigowatts/springboot_with_mybatis/repository/MessageRepositoryTest.sql")
    public void findAllTest() {
        List<Message> expected = new ArrayList<Message>();
        
        Message message01 = new Message();
        message01.setId(1);
        message01.setText("hello");
        expected.add(message01);
        Message message02 = new Message();
        message02.setId(2);
        message02.setText("hoge");
        expected.add(message02);
        Message message03 = new Message();
        message03.setId(3);
        message03.setText("foo");
        expected.add(message03);
        Message message04 = new Message();
        message04.setId(4);
        message04.setText("bar");
        expected.add(message04);

        List<Message> actual = messageRepository.findAll();
        assertThat(actual.size()).isEqualTo(expected.size());
    }

    @Test
    public void createTest() {
        Message message = new Message();
        message.setText("fizz");
        messageRepository.create(message);

        assertThat(messageRepository.count()).isEqualTo(2L);
    }

    @Test
    public void updateTest() {
        Message message = new Message();
        message.setId(1);
        message.setText("fizzbuzz");
        boolean actual = messageRepository.update(message);
        
        assertThat(actual).isTrue();
    }
    
    @Test
    @DisplayName("Message削除のテスト")
    public void deleteTest() {
        boolean actual = messageRepository.delete(1);

        assertThat(actual).isTrue();
    }
}