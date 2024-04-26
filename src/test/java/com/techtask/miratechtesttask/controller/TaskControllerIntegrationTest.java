package com.techtask.miratechtesttask.controller;

import com.techtask.miratechtesttask.repository.TaskRepository;
import com.techtask.miratechtesttask.service.TaskService;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@Testcontainers
@ExtendWith(SpringExtension.class)
public class TaskControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TaskRepository taskRepository;
    @MockBean
    private TaskService taskService;

    @BeforeEach
    void clearDatabase(@Autowired Flyway flyway) {
        flyway.clean();
        flyway.migrate();
    }
}
