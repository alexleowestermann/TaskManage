package com.example.server;

import com.example.server.model.Task;
import com.example.server.repository.TaskRepository;
import com.example.server.service.TaskService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    /**
     * Tests getting all tasks.
     */
    @Test
    void testGetAllTasks() {
        List<Task> tasks = new ArrayList<>();

        Task task1 = new Task();
        Task task2 = new Task();
        Task task3 = new Task();

        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);

        when(taskRepository.findAll()).thenReturn(tasks);

        assertEquals(3, taskService.getAllTasks().size());
    }

    /**
     * Tests renaming tasks.
     */
    @Test
    void testRenameTask(){
        Task task = new Task();
        task.setId(1L);
        task.setTaskName("John");

        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        when(taskRepository.save(any(Task.class))).thenReturn(task);

        Task taskWithNewName = taskService.renameTask(1L, "Jack");

        assertEquals("Jack", taskWithNewName.getTaskName());
    }

    /**
     * Tests adding a task.
     */
    @Test
    void testAddTask() {
        Task task = new Task();
        task.setTaskName("New Task");

        when(taskRepository.save(task)).thenReturn(task);

        Task result = taskService.addTask(task);

        assertEquals("New Task", result.getTaskName());
    }

    /**
     * Tests if getting a task by id exists.
     */
    @Test
    void testGetTaskByIdExists() {
        Task task = new Task();
        task.setId(42L);

        when(taskRepository.findById(42L)).thenReturn(Optional.of(task));

        Optional<Task> result = taskService.getTaskById(42L);

        assertTrue(result.isPresent());
        assertEquals(42L, result.get().getId());
    }

    /**
     * Tests the set completed status.
     */
    @Test
    void testSetCompletedStatus() {
        Task task = new Task();
        task.setId(7L);
        task.setIsCompleted(false);

        when(taskRepository.findById(7L)).thenReturn(Optional.of(task));
        when(taskRepository.save(any(Task.class))).thenReturn(task);

        Task result = taskService.setCompletedStatus(7L, true);

        assertTrue(result.getIsCompleted());
    }

    /**
     * Tests removing task by id.
     */

    @Test
    void testRemoveTaskById() {
        Long id = 99L;

        taskService.removeTaskById(id);

        verify(taskRepository).deleteById(id);
    }

    /**
     * Tests that the program throws is there exists no task by that id.
     */
    @Test
    void testRenameTaskThrowsIfMissing() {
        when(taskRepository.findById(123L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () ->
                taskService.renameTask(123L, "Renamed Task")
        );

        assertEquals("No Task exists by that ID", ex.getMessage());
    }

}