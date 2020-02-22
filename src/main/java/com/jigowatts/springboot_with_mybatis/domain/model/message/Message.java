package com.jigowatts.springboot_with_mybatis.domain.model.message;

import lombok.Data;

/**
 * Message
 */
@Data
public class Message {
    private int id;
    private String text;
    private String jsonbValue;

}