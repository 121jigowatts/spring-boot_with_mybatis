package com.jigowatts.springboot_with_mybatis.presentation.resource;

import java.io.Serializable;

import javax.validation.constraints.Size;

/**
 * MessageResourceQuery
 */
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

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDatabase() {
        return this.database;
    }

    public void setDatabase(String dbName) {
        this.database = dbName;
    }

    public String getSchema() {
        return this.schema;
    }

    public void setSchema(String schemaName) {
        this.schema = schemaName;
    }
}