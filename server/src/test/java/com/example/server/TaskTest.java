package com.example.server;

import com.example.server.model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {

    @BeforeEach
    public void setUp(){

    }

    /**
     * Tests setting the id.
     */
    @Test
    void testSetId(){
        Long id = 1L;

        Task task = new Task();

        task.setId(id);

        assertEquals(id, task.getId());
    }

    /**
     * Tests setting a task as completed.
     */
    @Test
    void testSetTaskCompleted(){
        boolean taskCompleted = true;

        Task task = new Task();

        task.setIsCompleted(taskCompleted);

        assertTrue(task.getIsCompleted());
    }

    /**
     * Test setting a tasks importance.
     */
    @Test
    void testSetTaskImportance(){
        // Task set to 1 (i.e. The highest priority)
        int taskImportance = 1;

        Task task = new Task();

        task.setTaskImportance(taskImportance);

        assertEquals(1, task.getTaskImportance());
    }

    /**
     * Tests setting and getting a tasks name.
     */
    @Test
    void testSetAndGetTaskName() {
        String name = "Write Report";
        Task task = new Task();
        task.setTaskName(name);
        assertEquals(name, task.getTaskName());
    }

    /**
     * Tests setting and getting a deadline.
     */
    @Test
    void testSetAndGetDeadline() {
        LocalDateTime deadline = LocalDateTime.of(2025, 12, 25, 10, 30);
        Task task = new Task();
        task.setDeadline(deadline);
        assertEquals(deadline, task.getDeadline());
    }


}
