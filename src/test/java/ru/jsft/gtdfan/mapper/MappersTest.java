package ru.jsft.gtdfan.mapper;

import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import ru.jsft.gtdfan.controller.mapper.*;
import ru.jsft.gtdfan.model.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MappersTest {

    @Test
    void categoryMapper() {
        Category category = Instancio.create(Category.class);
        assertThat(CategoryMapper.INSTANCE.toEntity(CategoryMapper.INSTANCE.toDto(category)))
                .isNotNull()
                .usingRecursiveComparison().isEqualTo(category);

        Note note = Instancio.create(Note.class);
        assertThat(NoteMapper.INSTANCE.toEntity(NoteMapper.INSTANCE.toDto(note)))
                .isNotNull()
                .usingRecursiveComparison().isEqualTo(note);

        Priority priority = Instancio.create(Priority.class);
        assertThat(PriorityMapper.INSTANCE.toEntity(PriorityMapper.INSTANCE.toDto(priority)))
                .isNotNull()
                .usingRecursiveComparison().isEqualTo(priority);

        // check with userId == null
        Task task = Instancio.create(Task.class);
        assertThat(TaskMapper.INSTANCE.toEntity(TaskMapper.INSTANCE.toDto(task)))
                .isNotNull()
                .usingRecursiveComparison().ignoringFields("created", "updated").isEqualTo(task);
        // check with userId != null
        task.setUserId(AggregateReference.to(1L));
        assertThat(TaskMapper.INSTANCE.toEntity(TaskMapper.INSTANCE.toDto(task)))
                .isNotNull()
                .usingRecursiveComparison().ignoringFields("created", "updated").isEqualTo(task);

        User user = Instancio.create(User.class);
        assertThat(UserMapper.INSTANCE.toEntity(UserMapper.INSTANCE.toDto(user)))
                .isNotNull()
                .usingRecursiveComparison().isEqualTo(user);
    }
}
