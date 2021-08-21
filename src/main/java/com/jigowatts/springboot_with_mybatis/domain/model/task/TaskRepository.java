package com.jigowatts.springboot_with_mybatis.domain.model.task;

import java.util.Optional;

public interface TaskRepository {
    public Optional<Task> getBy(String id);
}
