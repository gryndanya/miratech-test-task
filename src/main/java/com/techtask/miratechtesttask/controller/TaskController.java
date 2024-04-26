package com.techtask.miratechtesttask.controller;

import com.techtask.miratechtesttask.dto.TaskDto;
import com.techtask.miratechtesttask.exception.BadRequestException;
import com.techtask.miratechtesttask.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
    public ResponseEntity<List<TaskDto>> getAllTasks() {
        return ResponseEntity.ok(taskService.getAllTasks());
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
    public ResponseEntity<TaskDto> getTaskById(@PathVariable("id") Long taskId) {
        return ResponseEntity.ok(taskService.getTaskById(taskId));
    }

    @Operation(summary = "Create task")
    @ApiResponse(responseCode = "201", content = {
            @Content(schema = @Schema(implementation = TaskDto.class), mediaType = "application/json")},
            description = "Endpoint to create task")
    @PostMapping("/tasks")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<TaskDto> createTask(@Valid @RequestBody TaskDto taskDto, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            throw new BadRequestException("Validation error: " + bindingResult.getFieldError());
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.createTask(taskDto));
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
    public ResponseEntity<TaskDto> updateTask(@PathVariable("id") Long taskId,
                                              @Valid @RequestBody TaskDto taskDto,
                                              BindingResult bindingResult) {

        if(taskDto == null || bindingResult.hasErrors()) {
            throw new BadRequestException("Validation error: " + bindingResult.getFieldError());
        }

        return ResponseEntity.ok(taskService.updateTask(taskId, taskDto));
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
    public ResponseEntity<Void> deleteTaskById(@PathVariable("id") Long id) {
        taskService.deleteTaskById(id);
        return ResponseEntity.noContent().build();
    }
}
