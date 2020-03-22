package com.jigowatts.springboot_with_mybatis.util.converter;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jigowatts.springboot_with_mybatis.domain.model.message.Database;
import com.jigowatts.springboot_with_mybatis.domain.model.message.Message;
import com.jigowatts.springboot_with_mybatis.presentation.resource.MessageResource;
import com.jigowatts.springboot_with_mybatis.util.json.JsonConverter;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

/**
 * MessageResourceConverter
 */
@Component
@RequiredArgsConstructor
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
            e.printStackTrace();
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
                e.printStackTrace();
            }
        }
        resource.setJsonbValue(db);
        return resource;
    }
}