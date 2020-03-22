package com.jigowatts.springboot_with_mybatis.util.converter;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jigowatts.springboot_with_mybatis.domain.model.message.Database;
import com.jigowatts.springboot_with_mybatis.domain.model.message.Message;
import com.jigowatts.springboot_with_mybatis.domain.model.message.Schema;
import com.jigowatts.springboot_with_mybatis.presentation.resource.MessageResource;
import com.jigowatts.springboot_with_mybatis.util.json.JsonConverter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * MessageResourceConverterTest
 */
@SpringBootTest
public class MessageResourceConverterTest {

    @Mock
    JsonConverter<Database> jsonConverter;

    @InjectMocks
    MessageResourceConverter resourceConverter;

    private Message message;
    private MessageResource messageResource;
    private Database db;

    @BeforeEach
    public void setup() {
        this.message = new Message();
        message.setId(1);
        message.setText("hoge");
        message.setJsonbValue("json");

        this.messageResource = new MessageResource();
        messageResource.setId(1);
        messageResource.setText("hoge");
        List<Schema> schemas = List.of();
        this.db = new Database(1, "pg", schemas);
        messageResource.setJsonbValue(db);
    }

    @Test
    public void toEntityTest() {
        Message expected = this.message;
        try {
            doReturn("json").when(jsonConverter).convertToString(any(Database.class));
        } catch (JsonProcessingException e) {
            fail();
        }
        var actual = resourceConverter.toEntity(this.messageResource);
        assertThat(actual.getId()).isEqualTo(expected.getId());
        assertThat(actual.getText()).isEqualTo(expected.getText());
        assertThat(actual.getJsonbValue()).isEqualTo(expected.getJsonbValue());
    }

    @Test
    public void toResourceTest() {
        MessageResource expected = this.messageResource;
        try {
            doReturn(db).when(jsonConverter).convertToObject(anyString(), any());
        } catch (IOException e) {
            fail();
        }
        var actual = resourceConverter.toResource(this.message);
        assertThat(actual.getId()).isEqualTo(expected.getId());
        assertThat(actual.getText()).isEqualTo(expected.getText());
        assertThat(actual.getJsonbValue()).isEqualTo(expected.getJsonbValue());
    }
}