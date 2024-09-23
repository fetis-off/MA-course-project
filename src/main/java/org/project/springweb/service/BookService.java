package org.project.springweb.service;

import org.project.springweb.dto.BookDto;
import org.project.springweb.dto.BookSearchParametersDto;
import org.project.springweb.dto.CreateBookRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService {
    List<BookDto> getAll(Pageable pageable);

    BookDto getBookById(Long id);

    BookDto create(CreateBookRequestDto requestDto);

    BookDto update(Long id, CreateBookRequestDto requestDto);

    void delete(Long id);

    Page<BookDto> search(BookSearchParametersDto params, Pageable pageable);
}
