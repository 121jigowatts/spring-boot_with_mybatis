package com.jigowatts.springboot_with_mybatis.domain.model.message;

import java.io.Serializable;

import lombok.Builder;
import lombok.Getter;

/**
 * MessageCriteria
 */
@Builder
public class MessageCriteria implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Getter
    private String text;

    @Getter
    private String dbName;

    private String schemaName;

    public String getSchemaName() {
        if (this.schemaName != null) {
            return String.format("[{\"schema_name\":\"%s\"}]", this.schemaName);
        }
        return this.schemaName;
    }

}