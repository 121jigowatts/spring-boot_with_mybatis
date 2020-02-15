package com.jigowatts.springboot_with_mybatis.domain.model.message;

import java.io.Serializable;

/**
 * MessageCriteria
 */
public class MessageCriteria implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String text;
    private String dbName;
    private String schemaName;

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDbName() {
        return this.dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getSchemaName() {
        if (this.schemaName != null) {
            return String.format("[{\"schema_name\":\"%s\"}]", this.schemaName);
        }
        return this.schemaName;
    }

    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }
}