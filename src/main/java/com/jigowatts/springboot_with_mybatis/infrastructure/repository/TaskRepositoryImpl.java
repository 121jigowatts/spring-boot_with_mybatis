package com.jigowatts.springboot_with_mybatis.infrastructure.repository;

import java.util.Optional;

import com.jigowatts.springboot_with_mybatis.domain.model.task.Task;
import com.jigowatts.springboot_with_mybatis.domain.model.task.TaskRepository;

import org.springframework.stereotype.Repository;

@Repository
public class TaskRepositoryImpl implements TaskRepository {

    @Override
    public Optional<Task> getBy(String id) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
