package com.techtask.miratechtesttask.repository;

import com.techtask.miratechtesttask.model.entity.Task;
import com.techtask.miratechtesttask.model.enums.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long>, JpaSpecificationExecutor<Task> {
    List<Task> findByTitle(String title);
    List<Task> findByStatus(TaskStatus status);
}
