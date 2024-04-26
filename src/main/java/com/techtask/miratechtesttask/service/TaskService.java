package com.techtask.miratechtesttask.service;

import com.techtask.miratechtesttask.dto.TaskDto;
import com.techtask.miratechtesttask.model.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface TaskService {

    List<TaskDto> getAllTasks();
    TaskDto getTaskById(Long id);
    TaskDto createTask(TaskDto dto);
    TaskDto updateTask(Long id, TaskDto updatedTask);
    void deleteTaskById(Long id);
}
