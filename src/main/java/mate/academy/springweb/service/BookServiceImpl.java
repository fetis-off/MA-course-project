package mate.academy.springweb.service;

import lombok.RequiredArgsConstructor;
import mate.academy.springweb.dto.BookDto;
import mate.academy.springweb.dto.CreateBookRequestDto;
import mate.academy.springweb.exception.EntityNotFoundException;
import mate.academy.springweb.mapper.BookMapper;
import mate.academy.springweb.model.Book;
import mate.academy.springweb.repository.BookRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public List<BookDto> getAll() {
        return bookRepository.getAll().stream()
                .map(bookMapper::toDto)
                .toList();
    }

    @Override
    public BookDto getBookById(Long id) {
        Book book = bookRepository.getBookById(id).orElseThrow(
                () -> new EntityNotFoundException("Cannot find book with id: " + id)
        );
        return bookMapper.toDto(book);
    }

    @Override
    public BookDto create(CreateBookRequestDto requestDto) {
        Book book = bookMapper.toModel(requestDto);
        return bookMapper.toDto(bookRepository.create(book));
    }
}
