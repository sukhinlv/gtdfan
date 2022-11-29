package ru.jsft.gtdfan.mapper;

import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import ru.jsft.gtdfan.controller.mapper.CategoryMapper;
import ru.jsft.gtdfan.model.Category;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MappersTest {

    @Test
    void categoryMapper() {
        Category category = Instancio.create(Category.class);
        assertThat(CategoryMapper.INSTANCE.toEntity(CategoryMapper.INSTANCE.toDto(category)))
                .isNotNull()
                .usingRecursiveComparison().isEqualTo(category);
    }
}
