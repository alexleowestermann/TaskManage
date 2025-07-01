package com.example.server.model;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * This class creates a task entity stored in the H2 database.
 * Fields: name, status, importance, deadline, and completion.
 */

@Entity

public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String taskName;
    private String taskStatus;
    private boolean isCompleted;
    private int taskImportance;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime deadline;

    /**
     * Returns the id of the task.
     * @return
     */
    public Long getId(){
        return this.id;
    }

    /**
     * Sets the id of the task.
     * @param id
     */
    public void setId(Long id){
        this.id = id;
    }

    /**
     * Gets the tasks name.
     * @return
     */
    public String getTaskName(){
        return this.taskName;
    }

    /**
     * Sets the tasks name.
     * @param taskName
     */
    public void setTaskName(String taskName){
        this.taskName = taskName;
    }

    /**
     * Gets whether the task is completed.
     * @return
     */
    public boolean getIsCompleted(){
        return this.isCompleted;
    }

    /**
     * Sets the tasks completion.
     * @param isCompleted
     */
    public void setIsCompleted(boolean isCompleted){
        this.isCompleted = isCompleted;
    }

    /**
     * Gets the importance of the task (1-10).
     * @return
     */
    public int getTaskImportance(){
        return this.taskImportance;
    }

    /**
     * Sets the importance of the task.
     * @param taskImportance
     */
    public void setTaskImportance(int taskImportance){
        this.taskImportance = taskImportance;
    }

    public LocalDateTime getDeadline() {
        return this.deadline;
    }

    /**
     * Sets the deadline for the task.
     * @param deadline
     */
    public void setDeadline(LocalDateTime deadline){
        this.deadline = deadline;
    }
}
