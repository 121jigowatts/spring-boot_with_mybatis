package com.jigowatts.springboot_with_mybatis.domain.model.message;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Database
 */
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Database implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private int key;
    private String databaseName;
    List<Schema> schemas;

}