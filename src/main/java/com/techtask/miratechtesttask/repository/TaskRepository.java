package com.techtask.miratechtesttask.repository;

import com.techtask.miratechtesttask.model.entity.Task;
import com.techtask.miratechtesttask.model.enums.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByTitle(String title);
    List<Task> findByStatus(TaskStatus status);
}
