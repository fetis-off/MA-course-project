package org.project.springweb.service.book;

import org.project.springweb.dto.book.BookDto;
import org.project.springweb.dto.book.BookSearchParametersDto;
import org.project.springweb.dto.book.CreateBookRequestDto;
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
