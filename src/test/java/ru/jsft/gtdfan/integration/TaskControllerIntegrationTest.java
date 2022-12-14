package ru.jsft.gtdfan.integration;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.util.NestedServletException;
import ru.jsft.gtdfan.AbstractSpringBootTest;
import ru.jsft.gtdfan.error.NotFoundException;
import ru.jsft.gtdfan.model.Task;
import ru.jsft.gtdfan.web.controller.TaskController;
import ru.jsft.gtdfan.web.controller.dto.TaskDto;
import ru.jsft.gtdfan.web.controller.mapper.TaskMapper;
import ru.jsft.gtdfan.web.util.JsonUtil;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.jsft.gtdfan.testdata.TaskTestData.*;
import static ru.jsft.gtdfan.testdata.UserTestData.ADMIN;
import static ru.jsft.gtdfan.testdata.UserTestData.USER;
import static ru.jsft.gtdfan.utils.MockAuthorization.userHttpBasic;

public class TaskControllerIntegrationTest extends AbstractSpringBootTest {
    private static final String REST_URL = TaskController.REST_URL + "/";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TaskMapper mapper;

    @Nested
    class FindTask {
        @Test
        void shouldFindAllForUser() throws Exception {
            mockMvc.perform(get(REST_URL).with(userHttpBasic(ADMIN)))
                    .andExpect(status().isOk())
                    .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                    .andExpect(TASK_DTO_MATCHER.contentJson(ADMIN_TASKS));
        }

        @Test
        void shouldFindById() throws Exception {
            mockMvc.perform(get(REST_URL + "/9").with(userHttpBasic(USER)))
                    .andExpect(status().isOk())
                    .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                    .andExpect(TASK_DTO_MATCHER.contentJson(TASK_DTO_9));
        }

        @Test
        void shouldThrowWhenGetNotOwn() {
            NestedServletException parentException = assertThrows(NestedServletException.class,
                    () -> mockMvc.perform(get(REST_URL + "/1").with(userHttpBasic(USER))));

            Throwable cause = parentException.getCause();
            assertThat(cause)
                    .isInstanceOf(NotFoundException.class)
                    .hasMessageContaining("Task with id = 1 not found");
        }

        @Test
        void shouldThrowWhenGetNotFound() {
            NestedServletException parentException = assertThrows(NestedServletException.class,
                    () -> mockMvc.perform(get(REST_URL + "/100").with(userHttpBasic(USER))));

            Throwable cause = parentException.getCause();
            assertThat(cause)
                    .isInstanceOf(NotFoundException.class)
                    .hasMessageContaining("Task with id = 100 not found");
        }

        @Test
        void shouldNotGetForUnauthorized() throws Exception {
            mockMvc.perform(get(REST_URL)).andExpect(status().isUnauthorized());
        }
    }

    @Nested
    class CreateTask {
        @Test
        void shouldCreate() throws Exception {
            TaskDto expected = getNewTaskDto();

            MvcResult mvcResult = mockMvc.perform(post(REST_URL)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(JsonUtil.writeValue(expected))
                            .with(userHttpBasic(USER)))
                    .andExpect(status().isCreated())
                    .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                    .andReturn();

            TaskDto actual = JsonUtil.readValue(mvcResult.getResponse().getContentAsString(), TaskDto.class);
            expected.setId(actual.getId());
            assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
        }

        @Test
        void shouldThrowWhenTaskIsNotNew() {
            TaskDto expected = getNewTaskDto();
            expected.setId(100L);

            NestedServletException parentException = assertThrows(NestedServletException.class,
                    () -> mockMvc.perform(post(REST_URL)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(JsonUtil.writeValue(expected))
                            .with(userHttpBasic(USER))));

            Throwable cause = parentException.getCause();
            assertThat(cause)
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("Task must be new");
        }

        @Test
        void shouldNotCreateForUnauthorized() throws Exception {
            mockMvc.perform(post(REST_URL)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(JsonUtil.writeValue(mapper.toDto(new Task()))))
                    .andExpect(status().isUnauthorized());
        }
    }

    @Nested
    class DeleteTask {
        @Test
        void shouldDelete() {
            // Given
            // When
            // Then
        }

        @Test
        void shouldThrowWhenDeleteNotOwn() {
            // Given
            // When
            // Then
        }

        @Test
        void shouldThrowWhenDeleteNotFound() {
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
    }

    @Nested
    class UpdateTask {
        @Test
        void shouldUpdate() {
            // Given
            // When
            // Then
        }

        @Test
        void shouldThrowWhenUpdateNotOwn() {
            // Given
            // When
            // Then
        }

        @Test
        void shouldThrowWhenUpdateNotFound() {
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
}
