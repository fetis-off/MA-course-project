package org.project.springweb.service;

import org.project.springweb.dto.BookDto;
import org.project.springweb.dto.CreateBookRequestDto;

import java.util.List;

public interface BookService {
    List<BookDto> getAll();

    BookDto getBookById(Long id);

    BookDto create(CreateBookRequestDto requestDto);
}
