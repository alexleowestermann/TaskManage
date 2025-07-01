package com.example.server;

import com.example.server.model.Task;
import com.example.server.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import javax.swing.text.html.Option;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
public class TaskRepositoryTest {

    @Autowired
    private TaskRepository taskRepository;

    /**
     * Tests saving a task to the task repository.
     */
    @Test
    void testSaveTask(){
        Task task = new Task();
        task.setTaskName("John");
        Task savedTask = taskRepository.save(task);

        Optional<Task> taskExists = taskRepository.findById(savedTask.getId());
        assertFalse(taskExists.isEmpty());
        assertEquals("John", taskExists.get().getTaskName());
    }

}
