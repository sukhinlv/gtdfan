package ru.jsft.gtdfan.service;

import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.jsft.gtdfan.error.IllegalRequestDataException;
import ru.jsft.gtdfan.model.User;
import ru.jsft.gtdfan.repository.UserRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.when;

class UserServiceTest {

    private UserService underTest;

    @Mock
    private UserRepository repository;

    @Captor
    ArgumentCaptor<Long> idCaptor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        underTest = new UserService(repository);
    }

    @Test
    void shouldFindAll() {
        Iterable<User> expected = List.of(
                Instancio.create(User.class),
                Instancio.create(User.class)
        );
        when(repository.findAll()).thenReturn(expected);

        assertThat(underTest.findAll()).isNotNull().usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void shouldFindById() {
        User expected = Instancio.create(User.class);
        when(repository.findById(expected.getId())).thenReturn(Optional.of(expected));

        assertThat(underTest.findById(expected.getId())).isNotNull().usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void shouldThrowWhenNotFindById() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> underTest.findById(1L))
                .isInstanceOf(IllegalRequestDataException.class)
                .hasMessageContaining(String.format("User with id = %d not found", 1L));
    }

    @Test
    void shouldCreate() {
        User expected = Instancio.create(User.class);
        expected.setId(null);
        when(repository.save(expected)).thenReturn(expected);

        assertThat(underTest.create(expected)).isNotNull().usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void shouldThrowWhenCreateNotNew() {
        User meal = Instancio.create(User.class);

        assertThatThrownBy(() -> underTest.create(meal))
                .isInstanceOf(IllegalRequestDataException.class)
                .hasMessageContaining("User must be new (id = null)");
    }

    @Test
    void shouldDelete() {
        underTest.delete(1L);
        then(repository).should().deleteById(idCaptor.capture());

        assertThat(idCaptor.getValue()).isEqualTo(1L);
    }

    @Test
    void shouldUpdate() {
        User original = Instancio.create(User.class);
        User updated = Instancio.create(User.class);
        updated.setId(original.getId());
        when(repository.findById(original.getId())).thenReturn(Optional.of(original));
        when(repository.save(updated)).thenReturn(updated);

        User actual = underTest.update(original.getId(), updated);

        assertThat(actual).usingRecursiveComparison().isEqualTo(updated);
    }

    @Test
    void shouldThrowWhenUpdateWrongId() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> underTest.update(1L, new User()))
                .isInstanceOf(IllegalRequestDataException.class)
                .hasMessageContaining(String.format("User with id = %d not found", 1L));
    }
}