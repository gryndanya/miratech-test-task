package com.techtask.miratechtesttask.service.impl;

import com.techtask.miratechtesttask.dto.TaskDto;
import com.techtask.miratechtesttask.exception.TaskNotFoundException;
import com.techtask.miratechtesttask.mapper.TaskMapper;
import com.techtask.miratechtesttask.mapper.TaskStatusMapper;
import com.techtask.miratechtesttask.model.entity.Task;
import com.techtask.miratechtesttask.repository.TaskRepository;
import com.techtask.miratechtesttask.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final TaskStatusMapper taskStatusMapper;

    @Override
    public List<TaskDto> getAllTasks() {
        return taskRepository.findAll()
                .stream()
                .map(taskMapper::mapToTaskDto)
                .toList();
    }

    @Override
    public TaskDto getTaskById(Long id) {
        return taskRepository.findById(id)
                .map(taskMapper::mapToTaskDto)
                .orElseThrow(() -> new TaskNotFoundException("Task with id = " + id + " not found."));
    }

    @Override
    public TaskDto createTask(TaskDto dto) {
        return taskMapper.mapToTaskDto(taskRepository.save(taskMapper.mapToTask(dto)));
    }

    @Override
    public TaskDto updateTask(Long id, TaskDto updatedTask) {
        return taskRepository.findById(id)
                .map(currentTask -> {
                    currentTask.setTitle(updatedTask.getTitle());
                    currentTask.setDescription(updatedTask.getDescription());
                    currentTask.setStatus(taskStatusMapper.mapToTaskStatus(updatedTask.getStatus()));
                    return  taskRepository.save(currentTask);
                })
                .map(taskMapper::mapToTaskDto)
                .orElseThrow(() -> new TaskNotFoundException("Task with id = " + id + " not found."));
    }

    @Override
    public void deleteTaskById(Long id) {
        taskRepository.findById(id)
                .ifPresentOrElse(taskRepository::delete,
                    () -> {
                        throw new TaskNotFoundException("Task with id = " + id + " not found.");
                    }
                );


    }
}
