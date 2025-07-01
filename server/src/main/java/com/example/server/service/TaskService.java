package com.example.server.service;

import com.example.server.repository.TaskRepository;
import com.example.server.model.Task;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * The TaskService handles business logic for managing tasks.
 */

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }

    public Task addTask(Task task){
        return taskRepository.save(task);
    }

    public Optional<Task> getTaskById(Long id){
        return taskRepository.findById(id);
    }

    public void removeTask(Task task){
        taskRepository.delete(task);
    }

    /**
     * Renames the task.
     * @param id
     * @param name
     * @return
     */
    public Task renameTask(Long id, String name){
        Optional<Task> taskToRename = taskRepository.findById(id);
        // If there is a task that exists under that id
        if(!taskToRename.isEmpty()){
            Task task = taskToRename.get();
            task.setTaskName(name);
            return taskRepository.save(task);
        }
        else{
            throw new RuntimeException("No Task exists by that ID");
        }
    }

    /**
     * Removes the task by its id.
     * @param id
     */
    public void removeTaskById(Long id) {
        taskRepository.deleteById(id);
    }

    /**
     * Sets the task as completed.
     * @param id
     * @param isCompleted
     * @return
     */
    public Task setCompletedStatus(Long id, boolean isCompleted) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with id " + id));
        task.setIsCompleted(isCompleted);
        return taskRepository.save(task);
    }

}
