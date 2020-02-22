package com.jigowatts.springboot_with_mybatis.domain.model.message;

import java.io.Serializable;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * column
 */
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Column implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String columnName;
    private int sortOrder;

}