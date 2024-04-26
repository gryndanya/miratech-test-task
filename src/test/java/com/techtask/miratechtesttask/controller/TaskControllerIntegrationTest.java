package com.techtask.miratechtesttask.controller;

import com.techtask.miratechtesttask.dto.TaskDto;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.junit.jupiter.Testcontainers;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Testcontainers
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
public class TaskControllerIntegrationTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    TaskController taskController;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void clearDatabase(@Autowired Flyway flyway) {
        flyway.clean();
        flyway.migrate();
    }

    //I know about sneaky throws
    @Test
    @DisplayName("Successful GET all")
    void testGetAllSuccessful() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/tasks")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size").value(7))
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray());
    }

    @Test
    @DisplayName("Successful GET by Id")
    void testGetByIdSuccessful() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/tasks/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Title1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("Description1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("PENDING"));
    }

    @Test
    @DisplayName("Successful Create task")
    void testCreateTaskSuccessful() throws Exception {
        TaskDto taskDto = new TaskDto("test","test", "COMPLETED");
        mockMvc.perform(MockMvcRequestBuilders.put("/tasks/10")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(taskDto)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("test"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("test"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("COMPLETED"));
    }

    @Test
    @DisplayName("Successful Update task")
    void testUpdateTaskSuccessful() throws Exception {
        TaskDto taskDto = new TaskDto("test","test", "COMPLETED");
        mockMvc.perform(MockMvcRequestBuilders.put("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(taskDto)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("test"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("test"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("COMPLETED"));
    }

    @Test
    @DisplayName("Successful Delete task")
    void testDeleteTaskSuccessful() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/tasks/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
