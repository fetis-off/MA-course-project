package org.project.springweb.service;

import org.project.springweb.dto.BookDto;
import org.project.springweb.dto.CreateBookRequestDto;

import java.util.List;

public interface BookService {
    List<BookDto> getAll();

    BookDto getBookById(Long id);

    BookDto create(CreateBookRequestDto requestDto);

    BookDto update(Long id, BookDto requestDto);

    void delete(Long id);
}
