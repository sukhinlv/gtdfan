package ru.jsft.gtdfan.integration;

import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.util.NestedServletException;
import ru.jsft.gtdfan.AbstractSpringBootTest;
import ru.jsft.gtdfan.error.NotFoundException;
import ru.jsft.gtdfan.model.Task;
import ru.jsft.gtdfan.repository.TaskRepository;
import ru.jsft.gtdfan.web.controller.TaskController;
import ru.jsft.gtdfan.web.controller.dto.TaskDto;
import ru.jsft.gtdfan.web.controller.mapper.TaskMapper;
import ru.jsft.gtdfan.web.util.JsonUtil;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.jsft.gtdfan.testdata.TaskTestData.ADMIN_TASKS;
import static ru.jsft.gtdfan.testdata.TaskTestData.TASK_DTO_MATCHER;
import static ru.jsft.gtdfan.testdata.UserTestData.ADMIN;
import static ru.jsft.gtdfan.utils.MockAuthorization.userHttpBasic;

public class TaskControllerIntegrationTest extends AbstractSpringBootTest {
    private static final String REST_URL = TaskController.REST_URL + "/";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TaskRepository repository;

    @Autowired
    private TaskMapper mapper;

    @Test
    void shouldFindAllForUser() throws Exception {
        mockMvc.perform(get(REST_URL).with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(TASK_DTO_MATCHER.contentJson(ADMIN_TASKS));
    }

    @Test
    void shouldFindById() throws Exception {
        Task task = Instancio.create(Task.class);
        TaskDto taskDto = mapper.toDto(task);
        when(repository.findById(task.getId())).thenReturn(Optional.of(task));

        mockMvc.perform(get(REST_URL + "/" + taskDto.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(TASK_DTO_MATCHER.contentJson(taskDto));
    }

    @Test
    void shouldThrow_WhenGetNotExisted() {
        long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.empty());

        NestedServletException parentException = assertThrows(NestedServletException.class,
                () -> mockMvc.perform(get(REST_URL + "/" + id)));

        Throwable cause = parentException.getCause();
        assertThat(cause)
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining(String.format("Task with id = %d not found", 1L));
    }

    @Test
    void shouldCreate() throws Exception {
        Task expected = Task.builder()
                .name("Some test task")
                .until(LocalDateTime.of(2022, 12, 15, 10, 30))
                .categoryId(AggregateReference.to(1L))
                .priorityId(AggregateReference.to(1L))
                .userId(AggregateReference.to(1L))
                .build();

        MvcResult mvcResult = mockMvc.perform(post(REST_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.writeValue(mapper.toDto(expected)))
                        .with(userHttpBasic(ADMIN)))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();
        Task actual = mapper.toEntity(JsonUtil.readValue(mvcResult.getResponse().getContentAsString(), TaskDto.class));
        expected.setId(actual.getId());

        assertThat(actual).usingRecursiveComparison().ignoringFields("created", "updated").isEqualTo(expected);
    }

    @Test
    void shouldNotCreateForUnauthorized() {
        // Given
        // When
        // Then
    }

    @Test
    void shouldThrowWhenTaskIsNotNew() {
        // Given
        // When
        // Then
    }

    @Test
    void shouldCreateForbiddenForNoAdminRole() {
        // Given
        // When
        // Then
    }

    @Test
    void shouldDelete() {
        // Given
        // When
        // Then
    }

    @Test
    void shouldNotDeleteForUnauthorized() {
        // Given
        // When
        // Then
    }

    @Test
    void shouldUpdate() {
        // Given
        // When
        // Then
    }

    @Test
    void shouldNotUpdateForUnauthorized() {
        // Given
        // When
        // Then
    }
}
