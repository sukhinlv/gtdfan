package ru.jsft.gtdfan.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.jsft.gtdfan.AbstractControllerTest;
import ru.jsft.gtdfan.controller.dto.CategoryDto;
import ru.jsft.gtdfan.util.MatcherFactory;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
public class CategoryControllerTest extends AbstractControllerTest {

    private static final String REST_URL = CategoryController.REST_URL + "/";

    public static final MatcherFactory.Matcher<CategoryDto> CATEGORY_DTO_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(CategoryDto.class);

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldGetAllCategories() throws Exception {
        CategoryDto categoryDto1 = new CategoryDto("1 - Today");
        categoryDto1.setId(1L);
        CategoryDto categoryDto2 = new CategoryDto("2 - Week");
        categoryDto2.setId(2L);
        List<CategoryDto> dtoList = List.of(categoryDto1, categoryDto2);

        mockMvc.perform(get(REST_URL))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(CATEGORY_DTO_MATCHER.contentJson(dtoList));
    }
}
