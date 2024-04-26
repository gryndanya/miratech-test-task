package com.techtask.miratechtesttask.controller;

import com.techtask.miratechtesttask.dto.TaskDto;
import com.techtask.miratechtesttask.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@AllArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @Operation(summary = "Get all tasks")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = TaskDto.class), mediaType = "application/json")}
                    ,description = "Endpoint to get all current tasks")})
    @GetMapping("/tasks")
    @ResponseStatus(HttpStatus.OK)
    public List<TaskDto> getAllTasks() {
        return taskService.getAllTasks();
    }

    @Operation(summary = "Get task by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = TaskDto.class), mediaType = "application/json")}
                    ,description = "Endpoint to get task by id"),
            @ApiResponse(responseCode = "404", content = {
                    @Content(schema = @Schema(implementation = TaskDto.class), mediaType = "application/json")}
                    ,description = "Invalid id and task not founded")
    })
    @GetMapping("/tasks/{id}")
    public TaskDto getTaskById(@PathVariable("id") Long taskId) {
        return taskService.getTaskById(taskId);
    }

    @Operation(summary = "Create task")
    @ApiResponse(responseCode = "201", content = {
            @Content(schema = @Schema(implementation = TaskDto.class), mediaType = "application/json")},
            description = "Endpoint to create task")
    @PostMapping("/tasks")
    @ResponseStatus(HttpStatus.CREATED)
    public TaskDto createTask(@Valid @RequestBody TaskDto taskDto) {
        return taskService.createTask(taskDto);
    }

    @Operation(summary = "Update task")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = TaskDto.class), mediaType = "application/json")},
                    description = "Endpoint to update current task"),
            @ApiResponse(responseCode = "404", content = {
                    @Content(schema = @Schema(implementation = TaskDto.class), mediaType = "application/json")},
                    description = "Invalid id and task not founded")
    })
    @PutMapping("tasks/{id}")
    public TaskDto updateTask(@PathVariable("id") Long taskId,@Valid @RequestBody TaskDto taskDto) {
        return taskService.updateTask(taskId, taskDto);
    }

    @Operation(summary = "Delete task")
    @ApiResponses({
            @ApiResponse(responseCode = "204", content = {
                    @Content(schema = @Schema(implementation = TaskDto.class), mediaType = "application/json")},
                    description = "Endpoint to delete current task"),
            @ApiResponse(responseCode = "404", content = {
                    @Content(schema = @Schema(implementation = TaskDto.class), mediaType = "application/json")},
                    description = "Invalid id and task not founded")
    })
    @DeleteMapping("tasks/{id}")
    public void deleteTaskById(@PathVariable("id") Long id) {
        taskService.deleteTaskById(id);
    }
}
