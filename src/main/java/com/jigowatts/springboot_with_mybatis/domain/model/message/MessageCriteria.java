package com.jigowatts.springboot_with_mybatis.domain.model.message;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * MessageCriteria
 */
public class MessageCriteria implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Getter
    @Setter
    private String text;

    @Getter
    @Setter
    private String dbName;

    @Setter
    private String schemaName;

    public String getSchemaName() {
        if (this.schemaName != null) {
            return String.format("[{\"schema_name\":\"%s\"}]", this.schemaName);
        }
        return this.schemaName;
    }

}