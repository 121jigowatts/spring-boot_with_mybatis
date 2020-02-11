package com.jigowatts.springboot_with_mybatis.infrastructure.repository;

import com.jigowatts.springboot_with_mybatis.domain.model.message.Message;
import com.jigowatts.springboot_with_mybatis.domain.model.message.MessageCriteria;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

/**
 * MessageImplRepositoryTest
 */
// @MybatisTest
@SpringBootTest
public class MessageImplRepositoryTest {

    @InjectMocks
    private MessageImplRepository messageRepository = new MessageImplRepository();

    @Mock
    MessageMapper messageMapper;

    @Test
    public void findOneTest() {
        Message expected = new Message();
        expected.setId(1);
        expected.setText("hello");
        expected.setJsonbValue("{\"id\":\"001\"}");
        doReturn(expected).when(messageMapper).findOne(1);

        Message actual = messageRepository.findOne(1);

        assertThat(actual.getId()).isEqualTo(expected.getId());
        assertThat(actual.getText()).isEqualTo(expected.getText());
        assertThat(actual.getJsonbValue()).isEqualTo(expected.getJsonbValue());
    }

    @Test
    public void countTest() {
        long expected = 1L;
        doReturn(expected).when(messageMapper).count();
        long actual = messageRepository.count();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void findAllTest() {
        List<Message> expected = new ArrayList<Message>();

        Message message01 = new Message();
        message01.setId(1);
        message01.setText("hello");
        message01.setJsonbValue("{\"id\":\"001\"}");
        expected.add(message01);
        Message message02 = new Message();
        message02.setId(2);
        message02.setText("hoge");
        message02.setJsonbValue("{\"id\":\"002\"}");
        expected.add(message02);
        Message message03 = new Message();
        message03.setId(3);
        message03.setText("hana");
        message03.setJsonbValue("{\"id\":\"003\"}");
        expected.add(message03);
        Message message04 = new Message();
        message04.setId(4);
        message04.setText("honey");
        message04.setJsonbValue("{\"id\":\"004\"}");
        expected.add(message04);

        MessageCriteria criteria = new MessageCriteria();
        criteria.setText("h");
        doReturn(expected).when(messageMapper).findAllByCriteria(criteria);
        List<Message> actual = messageRepository.findAllByCriteria(criteria);
        assertThat(actual.size()).isEqualTo(expected.size());
    }

    @Test
    public void createTest() {
        Message message = new Message();
        message.setText("fizz");
        doNothing().when(messageMapper).create(message);

        messageRepository.create(message);

        verify(messageMapper, times(1)).create(message);
    }

    @Test
    public void updateTest() {
        Message message = new Message();
        message.setId(1);
        message.setText("fizzbuzz");
        doReturn(true).when(messageMapper).update(message);

        boolean actual = messageRepository.update(message);

        assertThat(actual).isTrue();
    }

    @Test
    @DisplayName("Message削除のテスト")
    public void deleteTest() {
        doReturn(true).when(messageMapper).delete(1);
        boolean actual = messageRepository.delete(1);

        assertThat(actual).isTrue();
    }
}