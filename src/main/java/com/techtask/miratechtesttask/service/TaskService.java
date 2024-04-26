package com.techtask.miratechtesttask.service;

import com.techtask.miratechtesttask.dto.TaskDto;

import java.util.List;


public interface TaskService {

    List<TaskDto> getAllTasks(String title, String status);
    TaskDto getTaskById(Long id);
    TaskDto createTask(TaskDto dto);
    TaskDto updateTask(Long id, TaskDto updatedTask);
    void deleteTaskById(Long id);
}
