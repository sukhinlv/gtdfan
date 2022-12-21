package ru.jsft.gtdfan.integration;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import ru.jsft.gtdfan.AbstractSpringBootTest;
import ru.jsft.gtdfan.error.IllegalRequestDataException;
import ru.jsft.gtdfan.model.Task;
import ru.jsft.gtdfan.service.TaskService;
import ru.jsft.gtdfan.web.controller.TaskController;
import ru.jsft.gtdfan.web.controller.dto.TaskDto;
import ru.jsft.gtdfan.web.controller.mapper.TaskMapper;
import ru.jsft.gtdfan.web.util.JsonUtil;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ru.jsft.gtdfan.testdata.TaskTestData.*;
import static ru.jsft.gtdfan.testdata.UserTestData.*;
import static ru.jsft.gtdfan.utils.MockAuthorization.userHttpBasic;

public class TaskControllerIntegrationTest extends AbstractSpringBootTest {
    private static final String REST_URL = TaskController.REST_URL + "/";

    @Autowired
    private TaskMapper mapper;

    @Autowired
    private TaskService taskService;

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
        void shouldThrowWhenGetNotOwn() throws Exception {
            mockMvc.perform(get(REST_URL + "/1")
                            .with(userHttpBasic(USER)))
                    .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                    .andExpect(status().isUnprocessableEntity())
                    .andExpect(jsonPath("$.message").value("Task with id = 1 not found"));
        }

        @Test
        void shouldThrowWhenGetNotFound() throws Exception {
            mockMvc.perform(get(REST_URL + "/100")
                            .with(userHttpBasic(USER)))
                    .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                    .andExpect(status().isUnprocessableEntity())
                    .andExpect(jsonPath("$.message").value("Task with id = 100 not found"));
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
                            .content(JsonUtil.objectToJson(expected))
                            .with(userHttpBasic(USER)))
                    .andExpect(status().isCreated())
                    .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                    .andReturn();

            TaskDto actual = JsonUtil.jsonToObject(mvcResult.getResponse().getContentAsString(), TaskDto.class);
            expected.setId(actual.getId());
            assertThat(actual).usingRecursiveComparison().isEqualTo(expected);

            assertThat(taskService.findById(actual.getId(), USER_DTO.getId()))
                    .usingRecursiveComparison()
                    .isEqualTo(mapper.toEntity(expected));
        }

        @Test
        void shouldThrowWhenTaskIsNotNew() throws Exception {
            TaskDto expected = getNewTaskDto();
            expected.setId(100L);

            mockMvc.perform(post(REST_URL)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(JsonUtil.objectToJson(expected))
                            .with(userHttpBasic(USER)))
                    .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                    .andExpect(status().isUnprocessableEntity())
                    .andExpect(jsonPath("$.message").value("Task must be new (id = null)"));
        }

        @Test
        void shouldNotCreateForUnauthorized() throws Exception {
            mockMvc.perform(post(REST_URL)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(JsonUtil.objectToJson(mapper.toDto(new Task()))))
                    .andExpect(status().isUnauthorized());
        }
    }

    @Nested
    class DeleteTask {
        @Test
        void shouldDelete() throws Exception {
            mockMvc.perform(delete(REST_URL + "/9").with(userHttpBasic(USER))).andExpect(status().isNoContent());
            assertThrows(IllegalRequestDataException.class, () -> taskService.findById(9L, USER_DTO.getId()));
        }

        @Test
        void shouldThrowWhenDeleteNotOwn() throws Exception {
            mockMvc.perform(delete(REST_URL + "/1")
                            .with(userHttpBasic(USER)))
                    .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                    .andExpect(status().isUnprocessableEntity())
                    .andExpect(jsonPath("$.message").value("Task with id = 1 not found"));
        }

        @Test
        void shouldThrowWhenDeleteNotFound() throws Exception {
            mockMvc.perform(delete(REST_URL + "/100")
                            .with(userHttpBasic(USER)))
                    .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                    .andExpect(status().isUnprocessableEntity())
                    .andExpect(jsonPath("$.message").value("Task with id = 100 not found"));
        }

        @Test
        void shouldNotDeleteForUnauthorized() throws Exception {
            mockMvc.perform(delete(REST_URL + "/9")).andExpect(status().isUnauthorized());
        }
    }

    @Nested
    class UpdateTask {
        @Test
        void shouldUpdate() throws Exception {
            TaskDto expected = getUpdatedTaskDto();

            MvcResult mvcResult = mockMvc.perform(put(REST_URL + "/" + TASK_DTO_10.getId())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(JsonUtil.objectToJson(expected))
                            .with(userHttpBasic(USER)))
                    .andExpect(status().isOk())
                    .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                    .andReturn();

            TaskDto actual = JsonUtil.jsonToObject(mvcResult.getResponse().getContentAsString(), TaskDto.class);
            expected.setId(actual.getId());
            assertThat(actual).usingRecursiveComparison().isEqualTo(expected);

            assertThat(taskService.findById(TASK_DTO_10.getId(), USER_DTO.getId()))
                    .usingRecursiveComparison()
                    .isEqualTo(mapper.toEntity(expected));
        }

        @Test
        void shouldThrowWhenUpdateNotOwn() throws Exception {
            TaskDto expected = getUpdatedTaskDto();

            mockMvc.perform(put(REST_URL + "/" + TASK_DTO_10.getId())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(JsonUtil.objectToJson(expected))
                            .with(userHttpBasic(ADMIN)))
                    .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                    .andExpect(status().isUnprocessableEntity())
                    .andExpect(jsonPath("$.message").value("Task with id = 10 not found"));
        }

        @Test
        void shouldThrowWhenUpdateNotFound() throws Exception {
            TaskDto expected = getUpdatedTaskDto();

            mockMvc.perform(put(REST_URL + "/100")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(JsonUtil.objectToJson(expected))
                            .with(userHttpBasic(USER)))
                    .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                    .andExpect(status().isUnprocessableEntity())
                    .andExpect(jsonPath("$.message").value("Task with id = 100 not found"));
        }

        @Test
        void shouldNotUpdateForUnauthorized() throws Exception {
            mockMvc.perform(delete(REST_URL + "/9")).andExpect(status().isUnauthorized());
        }
    }
}
