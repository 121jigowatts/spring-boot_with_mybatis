package com.jigowatts.springboot_with_mybatis.application.component;

import com.jigowatts.springboot_with_mybatis.domain.model.task.Task;
import com.jigowatts.springboot_with_mybatis.domain.model.task.TaskRepository;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TaskComponentImpl implements TaskComponent {
    private final TaskRepository taskRepository;

    @Override
    public Task getBy(String id) {
        return taskRepository.getBy(id).orElse(Task.builder().build());
    }

}
