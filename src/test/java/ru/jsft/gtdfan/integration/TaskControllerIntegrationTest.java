package ru.jsft.gtdfan.integration;

import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.NestedServletException;
import ru.jsft.gtdfan.AbstractControllerTest;
import ru.jsft.gtdfan.controller.TaskController;
import ru.jsft.gtdfan.controller.dto.TaskDto;
import ru.jsft.gtdfan.controller.mapper.TaskMapper;
import ru.jsft.gtdfan.error.NotFoundException;
import ru.jsft.gtdfan.model.Task;
import ru.jsft.gtdfan.repository.TaskRepository;
import ru.jsft.gtdfan.util.MatcherFactory;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
        List<Task> categoryList = List.of(
                Instancio.create(Task.class),
                Instancio.create(Task.class)
        );
        when(repository.findAll()).thenReturn(categoryList);

        List<TaskDto> dtoList = categoryList.stream()
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
        Task category = Instancio.create(Task.class);
        TaskDto categoryDto = TaskMapper.INSTANCE.toDto(category);
        when(repository.findById(category.getId())).thenReturn(Optional.of(category));

        mockMvc.perform(get(REST_URL + "/" + categoryDto.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(CATEGORY_DTO_MATCHER.contentJson(categoryDto));
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
}
