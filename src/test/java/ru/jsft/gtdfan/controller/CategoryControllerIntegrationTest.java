package ru.jsft.gtdfan.controller;

import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.NestedServletException;
import ru.jsft.gtdfan.AbstractControllerTest;
import ru.jsft.gtdfan.controller.dto.CategoryDto;
import ru.jsft.gtdfan.controller.mapper.CategoryMapper;
import ru.jsft.gtdfan.error.NotFoundException;
import ru.jsft.gtdfan.model.Category;
import ru.jsft.gtdfan.repository.CategoryRepository;
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
public class CategoryControllerIntegrationTest extends AbstractControllerTest {
    private static final MatcherFactory.Matcher<CategoryDto> CATEGORY_DTO_MATCHER =
            MatcherFactory.usingIgnoringFieldsComparator(CategoryDto.class);
    private static final String REST_URL = CategoryController.REST_URL + "/";
    private static final CategoryMapper mapper = Mappers.getMapper(CategoryMapper.class);

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryRepository repository;

    @Test
    void shouldGetAll() throws Exception {
        List<Category> categoryList = List.of(
                Instancio.create(Category.class),
                Instancio.create(Category.class)
        );
        when(repository.findAll()).thenReturn(categoryList);

        List<CategoryDto> dtoList = categoryList.stream()
                .map(mapper::toDto)
                .sorted(Comparator.comparing(CategoryDto::getName))
                .toList();

        mockMvc.perform(get(REST_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(CATEGORY_DTO_MATCHER.contentJson(dtoList));
    }

    @Test
    void shouldGet() throws Exception {
        Category category = Instancio.create(Category.class);
        CategoryDto categoryDto = mapper.toDto(category);
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
                .hasMessageContaining(String.format("Category with id = %d not found", 1L));
    }
}
