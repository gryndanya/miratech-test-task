package com.techtask.miratechtesttask.mapper;

import com.techtask.miratechtesttask.dto.TaskDto;
import com.techtask.miratechtesttask.model.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    Task mapToTask(TaskDto taskDto);
    TaskDto mapToTaskDto(Task task);

}
