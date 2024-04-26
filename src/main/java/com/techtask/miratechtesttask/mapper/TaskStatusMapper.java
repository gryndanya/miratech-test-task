package com.techtask.miratechtesttask.mapper;

import com.techtask.miratechtesttask.model.enums.TaskStatus;
import org.mapstruct.Mapper;
import org.mapstruct.ValueMapping;
import org.mapstruct.ValueMappings;

@Mapper(componentModel = "spring")
public interface TaskStatusMapper {

    @ValueMappings({
            @ValueMapping(source = "pending", target = "PENDING"),
            @ValueMapping(source = "completed", target = "COMPLETED"),
            @ValueMapping(source = "in progress", target = "IN_PROGRESS"),
            @ValueMapping(source = "declined", target = "DECLINED"),
            @ValueMapping(source = "reassigned", target = "REASSIGNED"),
            @ValueMapping(source = "review", target = "REVIEW"),
            @ValueMapping(source = "hold", target = "HOLD")
    })
    TaskStatus mapToTaskStatus(String status);

    @ValueMappings({
            @ValueMapping(source = "PENDING", target = "pending"),
            @ValueMapping(source = "COMPLETED", target = "completed"),
            @ValueMapping(source = "IN_PROGRESS", target = "in progress"),
            @ValueMapping(source = "DECLINED", target = "declined"),
            @ValueMapping(source = "REASSIGNED", target = "reassigned"),
            @ValueMapping(source = "REVIEW", target = "review"),
            @ValueMapping(source = "HOLD", target = "hold")
    })
    String mapToString(TaskStatus status);
}
