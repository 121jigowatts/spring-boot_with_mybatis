package com.jigowatts.springboot_with_mybatis.presentation.resource;

import java.io.IOException;
import java.io.Serializable;

import javax.validation.constraints.Size;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.jigowatts.springboot_with_mybatis.domain.model.message.Database;
import com.jigowatts.springboot_with_mybatis.domain.model.message.Message;
import com.jigowatts.springboot_with_mybatis.util.json.JsonConverter;

import lombok.Getter;
import lombok.Setter;

/**
 * MessageResource
 */
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class MessageResource implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    JsonConverter<Database> jsonConverter = new JsonConverter<>();

    @Getter
    @Setter
    private int id;
    
    @Size(max = 10)
    @Getter
    @Setter
    private String text;
    
    @Getter
    @Setter
    private Database jsonbValue;

    public Message toEntity() {
        Message entity = new Message();
        entity.setId(this.id);
        entity.setText(this.text);
        Database db = this.jsonbValue;
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