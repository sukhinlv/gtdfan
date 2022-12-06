package ru.jsft.gtdfan.mapper;

import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import ru.jsft.gtdfan.model.*;
import ru.jsft.gtdfan.web.controller.mapper.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MappersTest {

    @Test
    void categoryMapper() {
        Category category = Instancio.create(Category.class);
        assertThat(CategoryMapper.INSTANCE.toEntity(CategoryMapper.INSTANCE.toDto(category)))
                .isNotNull()
                .usingRecursiveComparison().isEqualTo(category);
    }

    @Test
    void noteMapper() {
        Note note = Instancio.create(Note.class);
        assertThat(NoteMapper.INSTANCE.toEntity(NoteMapper.INSTANCE.toDto(note)))
                .isNotNull()
                .usingRecursiveComparison().ignoringFields("updated").isEqualTo(note);
    }

    @Test
    void priorityMapper() {
        Priority priority = Instancio.create(Priority.class);
        assertThat(PriorityMapper.INSTANCE.toEntity(PriorityMapper.INSTANCE.toDto(priority)))
                .isNotNull()
                .usingRecursiveComparison().isEqualTo(priority);
    }

    @Test
    void taskMapper() {
        Task task = Instancio.create(Task.class);
        task.setCategoryId(AggregateReference.to(1L));
        task.setPriorityId(AggregateReference.to(1L));
        task.setUserId(AggregateReference.to(1L));
        assertThat(TaskMapper.INSTANCE.toEntity(TaskMapper.INSTANCE.toDto(task)))
                .isNotNull()
                .usingRecursiveComparison().ignoringFields("created", "updated").isEqualTo(task);
    }

    @Test
    void userMapper() {
        User user = Instancio.create(User.class);
        assertThat(UserMapper.INSTANCE.toEntity(UserMapper.INSTANCE.toDto(user)))
                .isNotNull()
                .usingRecursiveComparison().ignoringFields("created").isEqualTo(user);
    }
}
