package com.jigowatts.springboot_with_mybatis.domain.model.message;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Schema
 */
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Schema implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String schemaName;
    private List<Table> tables;

}