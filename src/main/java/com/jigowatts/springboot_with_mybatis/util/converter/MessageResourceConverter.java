package com.jigowatts.springboot_with_mybatis.util.converter;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jigowatts.springboot_with_mybatis.domain.model.message.Database;
import com.jigowatts.springboot_with_mybatis.domain.model.message.Message;
import com.jigowatts.springboot_with_mybatis.presentation.resource.MessageResource;
import com.jigowatts.springboot_with_mybatis.util.json.JsonConverter;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * MessageResourceConverter
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class MessageResourceConverter {

    private final JsonConverter<Database> jsonConverter;

    public Message toEntity(MessageResource resource) {
        Message entity = new Message();
        entity.setId(resource.getId());
        entity.setText(resource.getText());
        Database db = resource.getJsonbValue();
        String json = "";
        try {
            json = jsonConverter.convertToString(db);
        } catch (JsonProcessingException e) {
            log.error("エラー；", e);
        }
        entity.setJsonbValue(json);
        return entity;
    }

    public MessageResource toResource(Message message) {
        MessageResource resource = new MessageResource();
        resource.setId(message.getId());
        resource.setText(message.getText());
        Database db = null;
        String json = message.getJsonbValue();
        if (json != null) {
            try {
                db = jsonConverter.convertToObject(json, Database.class);
            } catch (IOException e) {
                log.error("エラー；", e);
            }
        }
        resource.setJsonbValue(db);
        return resource;
    }
}