package ru.jsft.gtdfan.service;

import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import ru.jsft.gtdfan.error.IllegalRequestDataException;
import ru.jsft.gtdfan.model.Task;
import ru.jsft.gtdfan.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.when;

class TaskServiceTest {

    private TaskService underTest;

    @Mock
    private TaskRepository repository;

    @Captor
    ArgumentCaptor<Long> idCaptor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        underTest = new TaskService(repository);
    }

    @Test
    void shouldFindAllForUser() {
        Iterable<Task> expected = List.of(
                Instancio.create(Task.class),
                Instancio.create(Task.class)
        );
        when(repository.findAllByUserId(1L)).thenReturn(expected);

        assertThat(underTest.findAllForUser(1L)).isNotNull().usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void shouldFindById() {
        Task expected = Instancio.create(Task.class);
        when(repository.findByIdAndUserId(expected.getId(), 1L)).thenReturn(Optional.of(expected));

        assertThat(underTest.findById(expected.getId(), 1L))
                .isNotNull().usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void shouldThrow_WhenNotFindById() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> underTest.findById(1L, 1L))
                .isInstanceOf(IllegalRequestDataException.class)
                .hasMessageContaining(String.format("Task with id = %d not found", 1L));
    }

    @Test
    void shouldCreate() {
        Task expected = Instancio.create(Task.class);
        expected.setId(null);
        when(repository.save(expected)).thenReturn(expected);

        assertThat(underTest.create(expected, 1L)).isNotNull().usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void shouldThrowWhenCreateNotNew() {
        Task meal = Instancio.create(Task.class);

        assertThatThrownBy(() -> underTest.create(meal, 1L))
                .isInstanceOf(IllegalRequestDataException.class)
                .hasMessageContaining("Task must be new (id = null)");
    }

    @Test
    void shouldDelete() {
        Task expected = Instancio.create(Task.class);
        expected.setUserId(AggregateReference.to(1L));
        when(repository.findByIdAndUserId(1L, 1L)).thenReturn(Optional.of(expected));

        underTest.delete(1L, expected.getUserId().getId());

        then(repository).should().deleteById(idCaptor.capture());
        assertThat(idCaptor.getValue()).isEqualTo(1L);
    }

    @Test
    void shouldUpdate() {
        Task original = Instancio.create(Task.class);
        original.setUserId(AggregateReference.to(1L));
        Task updated = Instancio.create(Task.class);
        updated.setId(original.getId());
        when(repository.findByIdAndUserId(original.getId(), 1L)).thenReturn(Optional.of(original));
        when(repository.save(updated)).thenReturn(updated);

        Task actual = underTest.update(original.getId(), updated, 1L);

        assertThat(actual).usingRecursiveComparison().isEqualTo(updated);
    }

    @Test
    void shouldThrowWhenUpdateWrongId() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> underTest.update(1L, new Task(), 1L))
                .isInstanceOf(IllegalRequestDataException.class)
                .hasMessageContaining(String.format("Task with id = %d not found", 1L));
    }

    @Test
    void shouldThrowWhenUpdateNotOwned() {
        Task notOwned = Instancio.create(Task.class);
        notOwned.setUserId(AggregateReference.to(2L));
        when(repository.findById(1L)).thenReturn(Optional.of(notOwned));

        assertThatThrownBy(() -> underTest.update(1L, new Task(), 1L))
                .isInstanceOf(IllegalRequestDataException.class)
                .hasMessageContaining(String.format("Task with id = %d not found", 1L));
    }
}