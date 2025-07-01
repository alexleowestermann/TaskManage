package com.example.server.controller;
import com.example.server.model.Task;
import com.example.server.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * REST controller.
 * Declares endpoints for managing tasks.
 */

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/tasks")
public class TaskController {

    public TaskService taskService;

    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    /**
     * Returns a list of tasks.
     * @return
     */
    @GetMapping
    public List<Task> getTasks(){
        return taskService.getAllTasks();
    }

    /**
     * Creates a new task.
     * @param task
     * @return
     */
    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return taskService.addTask(task);
    }

    /**
     *
     * @param id
     */
    @DeleteMapping("/{id}")
    public void removeTask(@PathVariable Long id) {
        taskService.removeTaskById(id);
    }

    /**
     * Renames a task.
     * @param id
     * @param updatedTask
     * @return
     */
    @PutMapping("/{id}")
    public Task renameTask(@PathVariable Long id, @RequestBody Task updatedTask) {
        return taskService.renameTask(id, updatedTask.getTaskName());
    }

    /**
     * Sets a task as completed.
     * @param id
     * @param body
     * @return
     */
    @PutMapping("/{id}/complete")
    public Task setTaskCompleted(@PathVariable Long id, @RequestBody Map<String, Boolean> body) {
        boolean isCompleted = body.getOrDefault("isCompleted", false);
        return taskService.setCompletedStatus(id, isCompleted);
    }


}
