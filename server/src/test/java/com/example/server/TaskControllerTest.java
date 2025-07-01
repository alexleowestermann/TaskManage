package com.example.server;

import com.example.server.controller.TaskController;
import com.example.server.model.Task;
import com.example.server.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class TaskControllerTest {

    private TaskService taskService;
    private TaskController taskController;

    @BeforeEach
    void setup() {
        taskService = mock(TaskService.class);
        taskController = new TaskController(taskService);
    }

    /**
     * Tests that all tasks can be collected
     */
    @Test
    public void testGetAllTasks() {
        Task task1 = new Task();
        task1.setId(1L);
        task1.setTaskName("Laundry");

        Task task2 = new Task();
        task2.setId(2L);
        task2.setTaskName("Work");

        Task task3 = new Task();
        task3.setId(3L);
        task3.setTaskName("Gym");

        List<Task> fakeTasks = Arrays.asList(task1, task2, task3);

        when(taskService.getAllTasks()).thenReturn(fakeTasks);

        assertEquals(3, taskController.getTasks().size());
    }

    /**
     * Tests creating a new task.
     */
    @Test
    void testCreateTask() {
        Task newTask = new Task();
        newTask.setTaskName("Buy milk");

        when(taskService.addTask(newTask)).thenReturn(newTask);

        Task result = taskController.createTask(newTask);

        assertEquals("Buy milk", result.getTaskName());
        verify(taskService).addTask(newTask);
    }

    /**
     * Tests removing a task.
     */
    @Test
    void testRemoveTask() {
        Long id = 1L;

        taskController.removeTask(id);

        verify(taskService).removeTaskById(id);
    }

    /**
     * Tests renaming a task.
     */
    @Test
    void testRenameTask() {
        Long id = 1L;
        Task input = new Task();
        input.setTaskName("Updated Name");

        Task renamed = new Task();
        renamed.setTaskName("Updated Name");

        when(taskService.renameTask(id, "Updated Name")).thenReturn(renamed);

        Task result = taskController.renameTask(id, input);

        assertEquals("Updated Name", result.getTaskName());
        verify(taskService).renameTask(id, "Updated Name");
    }

    /**
     * Tests setting a task as completed.
     */
    @Test
    void testSetTaskCompleted() {
        Long id = 1L;
        Map<String, Boolean> body = Map.of("isCompleted", true);

        Task updated = new Task();
        updated.setIsCompleted(true);

        when(taskService.setCompletedStatus(id, true)).thenReturn(updated);

        Task result = taskController.setTaskCompleted(id, body);

        assertTrue(result.getIsCompleted());
        verify(taskService).setCompletedStatus(id, true);
    }
}
