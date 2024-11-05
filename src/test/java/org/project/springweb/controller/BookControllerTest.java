package org.project.springweb.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.project.springweb.dto.book.BookDto;
import org.project.springweb.dto.book.CreateBookRequestDto;
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
public class BookControllerTest {
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

    @WithMockUser(username = "admin", roles = {"ADMIN"})
    @Test
    @DisplayName("Create a new book")
    void createBook_validRequest_Success() throws Exception {
        //Given
        CreateBookRequestDto requestDto = TestUtil.createBookRequestDto();
        String jsonRequest = objectMapper.writeValueAsString(requestDto);
        //When
        MvcResult result = mockMvc.perform(
                        post("/books")
                                .content(jsonRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andReturn();
        assertEquals(result.getResponse().getStatus(), HttpStatus.CREATED.value());
        //Then
        BookDto actual = objectMapper.readValue(result.getResponse().getContentAsString(),
                BookDto.class);
        assertNotNull(actual);
        compare(requestDto, actual);
    }

    @WithMockUser(username = "User", roles = "USER")
    @Test
    @DisplayName("Get all books")
    void getAll_valid_Success() throws Exception {
        //When
        MvcResult result = mockMvc.perform(
                        get("/books")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andReturn();
        assertEquals(result.getResponse().getStatus(), HttpStatus.OK.value());
        //Then
        BookDto[] actual = objectMapper.readValue(
                result.getResponse().getContentAsString(),
                BookDto[].class);
        assertNotNull(actual);
        assertEquals(actual.length, 3);
    }

    @WithMockUser(username = "User", roles = "USER")
    @Test
    @DisplayName("Get book by valid id")
    void getBookById_validId_Success() throws Exception {
        //Given
        CreateBookRequestDto expected = TestUtil.createBookRequestDto();
        expected.setIsbn(TestUtil.getSecondIsbn());
        //When
        MvcResult result = mockMvc.perform(
                        get("/books/{id}", validId)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andReturn();
        assertEquals(result.getResponse().getStatus(), HttpStatus.OK.value());
        //Then
        BookDto actual = objectMapper.readValue(
                result.getResponse().getContentAsString(),
                BookDto.class);
        assertNotNull(actual);
        compare(expected, actual);
    }

    @WithMockUser(username = "User", roles = "USER")
    @Test
    @DisplayName("Get book by invalid id")
    void getBookById_invalidId_Fail() throws Exception {
        //Given
        Long invalidId = -1L;
        //When
        mockMvc.perform(
                get("/books/{id}", invalidId)
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(
                        result.getResolvedException() instanceof EntityNotFoundException));
    }

    @Test
    @WithMockUser(username = "Admin", roles = "ADMIN")
    @DisplayName("Delete book by valid id")
    void deleteBookById_validId_Success() throws Exception {
        //When
        mockMvc.perform(delete("/books/{id}", validId)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNoContent())
                .andReturn();
    }

    void compare(CreateBookRequestDto expected, BookDto actual) {
        assertEquals(expected.getIsbn(), actual.getIsbn());
        assertEquals(expected.getTitle(), actual.getTitle());
        assertEquals(expected.getAuthor(), actual.getAuthor());
        assertEquals(0, expected.getPrice().compareTo(actual.getPrice()));
    }
}
