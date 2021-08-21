package com.jigowatts.springboot_with_mybatis.domain.model.task;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    private String id;
    private int number;
    private String name;
    private LocalDateTime start;
    private LocalDateTime end;
    private LocalDate expire;
    private boolean completed;
}
