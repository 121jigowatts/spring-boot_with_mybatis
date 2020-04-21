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
        Database db = resource.getJsonbValue();
        String json = "";
        try {
            json = jsonConverter.convertToString(db);
        } catch (JsonProcessingException e) {
            log.error("エラー；", e);
        }
        return Message.builder().id(resource.getId()).text(resource.getText()).jsonbValue(json).build();
    }

    public MessageResource toResource(Message message) {
        Database db = null;
        String json = message.getJsonbValue();
        if (json != null) {
            try {
                db = jsonConverter.convertToObject(json, Database.class);
            } catch (IOException e) {
                log.error("エラー；", e);
            }
        }
        return MessageResource.builder().id(message.getId()).text(message.getText()).jsonbValue(db).build();
    }
}