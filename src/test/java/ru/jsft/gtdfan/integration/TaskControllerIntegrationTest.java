package ru.jsft.gtdfan.integration;

import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.util.NestedServletException;
import ru.jsft.gtdfan.AbstractControllerTest;
import ru.jsft.gtdfan.controller.TaskController;
import ru.jsft.gtdfan.controller.dto.TaskDto;
import ru.jsft.gtdfan.controller.mapper.TaskMapper;
import ru.jsft.gtdfan.error.NotFoundException;
import ru.jsft.gtdfan.model.Task;
import ru.jsft.gtdfan.repository.TaskRepository;
import ru.jsft.gtdfan.util.JsonUtil;
import ru.jsft.gtdfan.util.MatcherFactory;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
public class TaskControllerIntegrationTest extends AbstractControllerTest {
    private static final MatcherFactory.Matcher<TaskDto> CATEGORY_DTO_MATCHER =
            MatcherFactory.usingIgnoringFieldsComparator(TaskDto.class);
    private static final String REST_URL = TaskController.REST_URL + "/";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskRepository repository;

    @Test
    void shouldGetAll() throws Exception {
        List<Task> taskList = List.of(
                Instancio.create(Task.class),
                Instancio.create(Task.class)
        );
        when(repository.findAll()).thenReturn(taskList);

        List<TaskDto> dtoList = taskList.stream()
                .map(TaskMapper.INSTANCE::toDto)
                .sorted(Comparator.comparing(TaskDto::getName))
                .toList();

        mockMvc.perform(get(REST_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(CATEGORY_DTO_MATCHER.contentJson(dtoList));
    }

    @Test
    void shouldGet() throws Exception {
        Task task = Instancio.create(Task.class);
        TaskDto taskDto = TaskMapper.INSTANCE.toDto(task);
        when(repository.findById(task.getId())).thenReturn(Optional.of(task));

        mockMvc.perform(get(REST_URL + "/" + taskDto.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(CATEGORY_DTO_MATCHER.contentJson(taskDto));
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
        Task expected = Instancio.create(Task.class);
        expected.setId(null);

        MvcResult mvcResult = mockMvc.perform(post(REST_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.writeValue(expected)))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();
        Task actual = JsonUtil.readValue(mvcResult.getResponse().getContentAsString(), Task.class);
        expected.setId(actual.getId());

        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }
}
