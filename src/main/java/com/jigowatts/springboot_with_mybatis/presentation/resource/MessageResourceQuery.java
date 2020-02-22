package com.jigowatts.springboot_with_mybatis.presentation.resource;

import java.io.Serializable;

import javax.validation.constraints.Size;

import lombok.Data;

/**
 * MessageResourceQuery
 */
@Data
public class MessageResourceQuery implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Size(max = 10)
    private String text;
    @Size(max = 20)
    private String database;
    @Size(max = 20)
    private String schema;

}