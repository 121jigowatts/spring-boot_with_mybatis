package com.jigowatts.springboot_with_mybatis.application.component;

import com.jigowatts.springboot_with_mybatis.domain.model.task.Task;

public interface TaskComponent {
    public Task getBy(String id);
}
