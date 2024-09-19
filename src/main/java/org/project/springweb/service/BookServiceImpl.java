package org.project.springweb.service;

import lombok.RequiredArgsConstructor;
import org.project.springweb.dto.BookDto;
import org.project.springweb.dto.CreateBookRequestDto;
import org.project.springweb.exception.EntityNotFoundException;
import org.project.springweb.mapper.BookMapper;
import org.project.springweb.model.Book;
import org.project.springweb.repository.BookRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public List<BookDto> getAll() {
        return bookRepository.findAll().stream()
                .map(bookMapper::toDto)
                .toList();
    }

    @Override
    public BookDto getBookById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Cannot find book with id: " + id)
        );
        return bookMapper.toDto(book);
    }

    @Override
    public BookDto create(CreateBookRequestDto requestDto) {
        Book book = bookMapper.toModel(requestDto);
        return bookMapper.toDto(bookRepository.save(book));
    }

    @Override
    public void delete(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Cannot find book with id: " + id));
        bookRepository.delete(book);
    }

    @Override
    public BookDto update(Long id, BookDto requestDto) {
        Book existedBook = bookRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Cannot find book with id: "
                        + id));
        bookMapper.updateBook(requestDto, existedBook);
        return bookMapper.toDto(bookRepository.save(existedBook));
    }
}
