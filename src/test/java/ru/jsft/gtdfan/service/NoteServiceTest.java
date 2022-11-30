package ru.jsft.gtdfan.service;

import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.jsft.gtdfan.error.NotFoundException;
import ru.jsft.gtdfan.model.Note;
import ru.jsft.gtdfan.repository.NoteRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.when;

class NoteServiceTest {

    private NoteService underTest;

    @Mock
    private NoteRepository repository;

    @Captor
    ArgumentCaptor<Long> idCaptor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        underTest = new NoteService(repository);
    }

    @Test
    void shouldFindAll() {
        Iterable<Note> expected = List.of(
                Instancio.create(Note.class),
                Instancio.create(Note.class)
        );
        when(repository.findAll()).thenReturn(expected);

        assertThat(underTest.findAll()).isNotNull().usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void shouldFindById() {
        Note expected = Instancio.create(Note.class);
        when(repository.findById(expected.getId())).thenReturn(Optional.of(expected));

        assertThat(underTest.findById(expected.getId())).isNotNull().usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void shouldThrow_WhenNotFindById() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> underTest.findById(1L))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining(String.format("Note with id = %d not found", 1L));
    }

    @Test
    void shouldCreate() {
        Note expected = Instancio.create(Note.class);
        expected.setId(null);
        when(repository.save(expected)).thenReturn(expected);

        assertThat(underTest.create(expected)).isNotNull().usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void shouldThrowWhenCreateNotNew() {
        Note meal = Instancio.create(Note.class);

        assertThatThrownBy(() -> underTest.create(meal))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Note must be new");
    }

    @Test
    void shouldDelete() {
        underTest.delete(1L);
        then(repository).should().deleteById(idCaptor.capture());

        assertThat(idCaptor.getValue()).isEqualTo(1L);
    }

    @Test
    void shouldUpdate() {
        Note original = Instancio.create(Note.class);
        Note updated = Instancio.create(Note.class);
        updated.setId(original.getId());
        when(repository.findById(original.getId())).thenReturn(Optional.of(original));
        when(repository.save(updated)).thenReturn(updated);

        Note actual = underTest.update(original.getId(), updated);

        assertThat(actual).usingRecursiveComparison().isEqualTo(updated);
    }

    @Test
    void shouldThrowWhenUpdateWrongId() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> underTest.update(1L, new Note()))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining(String.format("Note with id = %d not found", 1L));
    }
}