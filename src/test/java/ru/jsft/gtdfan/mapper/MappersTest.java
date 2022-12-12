package ru.jsft.gtdfan.mapper;

import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import ru.jsft.gtdfan.AbstractSpringBootTest;
import ru.jsft.gtdfan.model.*;
import ru.jsft.gtdfan.web.controller.mapper.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MappersTest extends AbstractSpringBootTest {

    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private NoteMapper noteMapper;
    @Autowired
    private PriorityMapper priorityMapper;
    @Autowired
    private TaskMapper taskMapper;
    @Autowired
    private UserMapper userMapper;

    @Test
    void categoryMapper() {
        Category category = Instancio.create(Category.class);
        assertThat(categoryMapper.toEntity(categoryMapper.toDto(category)))
                .isNotNull()
                .isEqualTo(category);
    }

    @Test
    void noteMapper() {
        Note note = Instancio.create(Note.class);
        assertThat(noteMapper.toEntity(noteMapper.toDto(note)))
                .isNotNull()
                .isEqualTo(note);
    }

    @Test
    void priorityMapper() {
        Priority priority = Instancio.create(Priority.class);
        assertThat(priorityMapper.toEntity(priorityMapper.toDto(priority)))
                .isNotNull()
                .isEqualTo(priority);
    }

    @Test
    void taskMapper() {
        Task task = Instancio.create(Task.class);
        task.setCategoryId(AggregateReference.to(1L));
        task.setPriorityId(AggregateReference.to(1L));
        task.setUserId(AggregateReference.to(1L));
        assertThat(taskMapper.toEntity(taskMapper.toDto(task)))
                .isNotNull()
                .isEqualTo(task);
    }

    @Test
    void userMapper() {
        User expected = Instancio.create(User.class);
        User actual = userMapper.toEntity(userMapper.toDto(expected));
        expected.setPassword("***");
        expected.setEmail(expected.getEmail().toLowerCase());
        assertThat(actual)
                .isNotNull()
                .isEqualTo(expected);
    }
}
