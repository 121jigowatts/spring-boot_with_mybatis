package com.jigowatts.springboot_with_mybatis.domain.model.message;

import lombok.Builder;
import lombok.Data;

/**
 * Message
 */
@Data
@Builder
public class Message {
    private int id;
    private String text;
    private String jsonbValue;

}