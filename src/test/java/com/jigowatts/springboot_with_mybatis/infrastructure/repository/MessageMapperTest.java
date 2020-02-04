package com.jigowatts.springboot_with_mybatis.infrastructure.repository;

import com.jigowatts.springboot_with_mybatis.domain.model.message.Message;
import com.jigowatts.springboot_with_mybatis.domain.model.message.MessageCriteria;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

/**
 * MessageMapperTest
 */
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // @MybatisTestを付与するとデフォルトで組み込みデータベースを使用する設定となる
public class MessageMapperTest {

    @Autowired
    MessageMapper messageMapper;

    @Test
    public void findOneTest() {
        Message expected = new Message();
        expected.setId(1);
        expected.setText("hello postgres");
        Message actual = messageMapper.findOne(1);

        assertThat(actual.getId()).isEqualTo(expected.getId());
        assertThat(actual.getText()).isEqualTo(expected.getText());
    }

    @Test
    public void countTest() {
        long expected = 1L;
        long actual = messageMapper.count();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @Sql(scripts = "/com/jigowatts/springboot_with_mybatis/infrastructure/repository/MessageMapperTest.sql")
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
        message03.setText("hana");
        expected.add(message03);
        Message message04 = new Message();
        message04.setId(4);
        message04.setText("honey");
        expected.add(message04);

        MessageCriteria criteria = new MessageCriteria();
        criteria.setText("h");
        List<Message> actual = messageMapper.findAllByCriteria(criteria);
        assertThat(actual.size()).isEqualTo(expected.size());
    }

    @Test
    public void createTest() {
        Message message = new Message();
        message.setText("fizz");
        messageMapper.create(message);

        assertThat(messageMapper.count()).isEqualTo(2L);
    }

    @Test
    public void updateTest() {
        Message message = new Message();
        message.setId(1);
        message.setText("fizzbuzz");
        boolean actual = messageMapper.update(message);

        assertThat(actual).isTrue();
    }

    @Test
    @DisplayName("Message削除のテスト")
    public void deleteTest() {
        boolean actual = messageMapper.delete(1);

        assertThat(actual).isTrue();
    }
}