package mate.academy.springweb.service;

import mate.academy.springweb.dto.BookDto;
import mate.academy.springweb.dto.CreateBookRequestDto;
import java.util.List;

public interface BookService {
    List<BookDto> getAll();

    BookDto getBookById(Long id);

    BookDto create(CreateBookRequestDto requestDto);
}
