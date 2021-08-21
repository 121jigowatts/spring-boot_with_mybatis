package com.jigowatts.springboot_with_mybatis.application.component;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import com.jigowatts.springboot_with_mybatis.domain.model.task.Task;
import com.jigowatts.springboot_with_mybatis.domain.model.task.TaskRepository;
import com.jigowatts.springboot_with_mybatis.helper.yaml.constructor.ExtensionConstructorBase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

@SpringBootTest
public class TaskComponentTest {
    @InjectMocks
    private TaskComponentImpl target;
    @Mock
    private TaskRepository taskRepository;

    @BeforeEach
    void setup() {
    }

    @Test
    public void test() throws IOException {

        Task expected = Task.builder().id("1").number(101).name("Homework")
                .start(LocalDateTime.of(2021, 8, 20, 16, 30, 0)).end(null).expire(LocalDate.of(2021, 8, 22))
                .completed(false).build();

        var path = Paths.get("src/test/resources/fixture/task/getByIdTest_not_completed.yml");
        try (InputStream io = Files.newInputStream(path)) {
            Constructor constructor = new ExtensionConstructorBase(Task.class);
            Yaml yaml = new Yaml(constructor);
            Task actual = yaml.load(io);

            assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
        }
    }

    @Test
    public void getByIdTest_not_completed() throws IOException {
        String id = "1";

        var path = Paths.get("src/test/resources/fixture/task/getByIdTest_not_completed.yml");
        try (InputStream io = Files.newInputStream(path)) {
            Constructor constructor = new ExtensionConstructorBase(Task.class);
            Yaml yaml = new Yaml(constructor);
            Task expected = yaml.load(io);

            when(taskRepository.getBy(id)).thenReturn(Optional.of(expected));

            var actual = target.getBy(id);

            assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
            verify(taskRepository, times(1)).getBy(id);
        }
    }
}
