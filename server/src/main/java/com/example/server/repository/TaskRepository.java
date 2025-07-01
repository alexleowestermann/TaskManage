package com.example.server.repository;

//import com.example.server.model.User;
import com.example.server.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Provides CRUD operations.
 */

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {





}




