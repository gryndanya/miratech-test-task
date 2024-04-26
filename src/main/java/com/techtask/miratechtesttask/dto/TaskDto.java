package com.techtask.miratechtesttask.dto;

import com.techtask.miratechtesttask.annotation.TaskStatusValidation;
import com.techtask.miratechtesttask.model.enums.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TaskDto {

    @NotBlank(message = "Title must not be null")
    @Size(max = 255)
    private String title;

    @NotBlank(message = "Description must not be null")
    @Size(max = 255)
    private String description;

    @NotBlank(message = "Status must not be null")
    @TaskStatusValidation(enumClazz = TaskStatus.class)
    private String status;
}
