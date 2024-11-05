package org.project.springweb.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.testcontainers.shaded.org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.project.springweb.dto.category.CategoryDto;
import org.project.springweb.dto.category.CreateCategoryRequestDto;
import org.project.springweb.exception.EntityNotFoundException;
import org.project.springweb.util.TestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@Sql(scripts = {"classpath:database/categories/add-three-categories-to-categories.sql",
        "classpath:database/books/add-three-books.sql"})
@Sql(scripts = {"classpath:database/categories/delete-categories-from-category.sql",
        "classpath:database/books/delete-books.sql"},
        executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CategoryControllerTest {
    protected static MockMvc mockMvc;
    private static final Long validId = 1L;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeAll
    static void beforeAll(
            @Autowired WebApplicationContext webApplicationContext
    ) {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();
    }

    @Sql(scripts = "classpath:database/categories/delete-categories-from-category.sql",
            executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    @Test
    @DisplayName("Create category")
    void createCategory_validRequest_ReturnsCategoryDto() throws Exception {
        //Given
        CreateCategoryRequestDto requestDto = TestUtil.createCategoryRequestDto();
        String jsonRequest = objectMapper.writeValueAsString(requestDto);
        CategoryDto expected = TestUtil.createCategoryResponseDto();
        //When
        MvcResult result = mockMvc.perform(
                        post("/categories")
                                .content(jsonRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andReturn();
        assertEquals(result.getResponse().getStatus(), HttpStatus.CREATED.value());
        //Then
        CategoryDto actual = objectMapper.readValue(
                result.getResponse().getContentAsString(),
                CategoryDto.class);
        assertNotNull(actual);
        assertTrue(reflectionEquals(expected, actual, "id"));
    }

    @Test
    @WithMockUser(username = "User", roles = "USER")
    @DisplayName("Get category by valid id")
    void getCategoryById_validId_Fail() throws Exception {
        //Given
        CategoryDto expected = TestUtil.createCategoryResponseDto();
        //When
        MvcResult result = mockMvc.perform(
                        get("/categories/{id}", validId)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andReturn();
        assertEquals(result.getResponse().getStatus(), HttpStatus.OK.value());
        //Then
        CategoryDto actual = objectMapper.readValue(
                result.getResponse().getContentAsString(),
                CategoryDto.class);
        assertNotNull(actual);
        assertTrue(reflectionEquals(expected, actual, "id"));
    }

    @Test
    @WithMockUser(username = "User", roles = "USER")
    @DisplayName("Get category by invalid id")
    void getBookById_invalidId_Fail() throws Exception {
        //Given
        Long invalidId = -1L;
        //When
        mockMvc.perform(
                        get("/categories/{id}", invalidId)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(
                        result.getResolvedException() instanceof EntityNotFoundException));
    }

    @Test
    @WithMockUser(username = "Admin", roles = "ADMIN")
    @DisplayName("Delete category by id")
    void deleteCategoryById_validId_Success() throws Exception {
        //When
        mockMvc.perform(
                        delete("/categories/{id}", validId)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andReturn();
    }

    @Test
    @WithMockUser(username = "User", roles = "USER")
    @DisplayName("Get all categories")
    void getAllCategories_validSize_Success() throws Exception {
        //When
        MvcResult result = mockMvc.perform(
                        get("/categories")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andReturn();
        assertEquals(result.getResponse().getStatus(), HttpStatus.OK.value());
        //Then
        CategoryDto[] actual = objectMapper.readValue(
                result.getResponse().getContentAsString(),
                CategoryDto[].class);
        assertNotNull(actual);
        assertEquals(actual.length, 3);
    }

    @Test
    @WithMockUser(username = "ADMIN", roles = "ADMIN")
    @DisplayName("Update category by id")
    void updateCategory_validId_Success() throws Exception {
        //Given
        String jsonRequest = objectMapper.writeValueAsString(TestUtil.updateCategoryRequestDto());
        //When
        MvcResult result = mockMvc.perform(
                        put("/categories/{id}", validId)
                                .content(jsonRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andReturn();
        //Then
        CategoryDto actual = objectMapper.readValue(
                result.getResponse().getContentAsString(),
                CategoryDto.class);
        assertNotNull(actual);
        assertTrue(reflectionEquals(TestUtil.updateCategoryResponseDto(), actual, "id"));
    }
}
