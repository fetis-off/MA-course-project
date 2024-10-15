package org.project.springweb.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.project.springweb.dto.book.BookDto;
import org.project.springweb.dto.book.BookSearchParametersDto;
import org.project.springweb.dto.book.CreateBookRequestDto;
import org.project.springweb.service.book.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    @Operation(summary = "Get all books")
    @PreAuthorize("hasRole('USER')")
    @GetMapping
    public List<BookDto> getAll(@PageableDefault(size = 20) Pageable pageable) {
        return bookService.getAll(pageable);
    }

    @Operation(summary = "Get a book", description = "Get a particular book by id")
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{id}")
    public BookDto getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    @Operation(summary = "Create a new book", description = "Create a new book")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookDto createBook(@RequestBody @Valid CreateBookRequestDto requestDto) {
        return bookService.create(requestDto);
    }

    @Operation(summary = "Delete a book", description = "Delete a particular book by id")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteBookById(@PathVariable Long id) {
        bookService.delete(id);
    }

    @Operation(summary = "Search books", description = "Search books by parameters")
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/search")
    public Page<BookDto> searchBooks(BookSearchParametersDto searchParameters,
                                     @PageableDefault(size = 20) Pageable pageable) {
        return bookService.search(searchParameters, pageable);
    }

    @Operation(summary = "Update a book", description = "Update a particular book by id")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public BookDto updateBook(@PathVariable Long id,
                              @RequestBody @Valid CreateBookRequestDto requestDto) {
        return bookService.update(id, requestDto);
    }
}
