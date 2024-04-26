package com.techtask.miratechtesttask.service.impl;

import com.techtask.miratechtesttask.dto.TaskDto;
import com.techtask.miratechtesttask.exception.TaskNotFoundException;
import com.techtask.miratechtesttask.mapper.TaskMapper;
import com.techtask.miratechtesttask.mapper.TaskStatusMapper;
import com.techtask.miratechtesttask.model.entity.Task;
import com.techtask.miratechtesttask.model.enums.TaskStatus;
import com.techtask.miratechtesttask.repository.TaskRepository;
import com.techtask.miratechtesttask.service.TaskService;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;


@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final TaskStatusMapper taskStatusMapper;


    @Override
    @Transactional(readOnly = true)
    public List<TaskDto> getAllTasks(String title, String status) {

        List<Task> tasks;
        if(!Objects.equals(title, null) && !Objects.equals(status, null)){
            tasks = taskRepository.findAll(byTitleAndStatus(title, taskStatusMapper.mapToTaskStatus(status)));
        }else if(!Objects.equals(title, null)){
            tasks = taskRepository.findAll(byTitle(title));
        }else if(!Objects.equals(status, null)){
            tasks = taskRepository.findAll(byStatus(taskStatusMapper.mapToTaskStatus(status)));
        }else{
            tasks = taskRepository.findAll();
        }
        return tasks.stream()
                .map(taskMapper::mapToTaskDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public TaskDto getTaskById(Long id) {
        return taskRepository.findById(id)
                .map(taskMapper::mapToTaskDto)
                .orElseThrow(() -> new TaskNotFoundException("Task with id = " + id + " not found."));
    }

    @Override
    @Transactional
    public TaskDto createTask(TaskDto dto) {
        return taskMapper.mapToTaskDto(taskRepository.save(taskMapper.mapToTask(dto)));
    }

    @Override
    @Transactional
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
    @Transactional
    public void deleteTaskById(Long id) {
        taskRepository.findById(id)
                .ifPresentOrElse(taskRepository::delete,
                    () -> {
                        throw new TaskNotFoundException("Task with id = " + id + " not found.");
                    }
                );
    }

    public static Specification<Task> byTitleAndStatus(String title, TaskStatus status) {
        return (root, query, criteriaBuilder) -> {
            Predicate predicateTitle = criteriaBuilder.equal(root.get("title"), title);
            Predicate predicateDescription = criteriaBuilder.equal(root.get("description"), status);
            return criteriaBuilder.and(predicateTitle, predicateDescription);
        };
    }

    static Specification<Task> byStatus(TaskStatus status) {
        return Specification.where((root, query, builder) ->
                builder.equal(root.get("status"), status));
    }

    static Specification<Task> byTitle(String title) {
        return Specification.where((root, query, builder) ->
                builder.equal(root.get("title"), title));
    }

}
