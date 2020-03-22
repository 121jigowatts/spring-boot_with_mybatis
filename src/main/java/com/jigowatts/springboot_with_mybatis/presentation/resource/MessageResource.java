package com.jigowatts.springboot_with_mybatis.presentation.resource;

import java.io.Serializable;

import javax.validation.constraints.Size;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.jigowatts.springboot_with_mybatis.domain.model.message.Database;

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
}