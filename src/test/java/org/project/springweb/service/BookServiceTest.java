package org.project.springweb.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.project.springweb.dto.book.BookDto;
import org.project.springweb.dto.book.BookDtoWithoutCategoryIds;
import org.project.springweb.dto.book.CreateBookRequestDto;
import org.project.springweb.mapper.BookMapper;
import org.project.springweb.model.Book;
import org.project.springweb.repository.book.BookRepository;
import org.project.springweb.service.book.BookServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
    @Mock
    private BookRepository bookRepository;

    @Mock
    private BookMapper bookMapper;

    @InjectMocks
    private BookServiceImpl bookService;

    @Test
    @DisplayName("Find book by valid id")
    public void getBook_WithValidBookId_ShouldReturnValidBook() {
        //Given
        Long bookId = 1L;
        Book book = new Book();
        BookDto expected = new BookDto();
        //When
        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));
        when(bookMapper.toDto(book)).thenReturn(expected);
        BookDto actual = bookService.getBookById(bookId);
        //Then
        assertEquals(expected, actual);
        verify(bookRepository, Mockito.times(1)).findById(bookId);
        verify(bookMapper, Mockito.times(1)).toDto(book);
    }

    @Test
    @DisplayName("Find all books")
    public void findAll_ValidPageable_ReturnsAllBooks() {
        //Given
        Book firstBook = new Book();
        Book secondBook = new Book();
        Page<Book> bookPage = new PageImpl<>(List.of(firstBook, secondBook));
        Pageable pageable = Pageable.ofSize(1);
        BookDto firstBookDto = new BookDto();
        BookDto secondBookDto = new BookDto();
        final List<BookDto> expected = List.of(firstBookDto, secondBookDto);
        //When
        when(bookMapper.toDto(firstBook)).thenReturn(firstBookDto);
        when(bookMapper.toDto(secondBook)).thenReturn(secondBookDto);
        when(bookRepository.findAll(pageable)).thenReturn(bookPage);
        List<BookDto> actual = bookService.findAll(pageable);
        //Then
        assertEquals(expected, actual);
        verify(bookRepository, Mockito.times(1)).findAll(pageable);
        verify(bookMapper, Mockito.times(1)).toDto(firstBook);
        verify(bookMapper, Mockito.times(1)).toDto(secondBook);
    }

    @Test
    @DisplayName("Save valid book")
    public void create_ValidBookRequest_ReturnsBookDto() {
        //Given
        CreateBookRequestDto requestDto = new CreateBookRequestDto();
        Book book = new Book();
        BookDto expected = new BookDto();
        //When
        when(bookMapper.toModel(requestDto)).thenReturn(book);
        when(bookRepository.save(book)).thenReturn(book);
        when(bookMapper.toDto(book)).thenReturn(expected);
        BookDto actual = bookService.create(requestDto);
        //Then
        assertEquals(expected, actual);
        verify(bookRepository, Mockito.times(1)).save(book);
        verify(bookMapper, Mockito.times(1)).toModel(requestDto);
        verify(bookMapper, Mockito.times(1)).toDto(book);
    }

    @Test
    @DisplayName("Update book by valid id")
    public void updateBook_ValidBookRequest_Ok() {
        //Given
        Long bookId = 1L;
        CreateBookRequestDto requestDto = new CreateBookRequestDto();
        Book book = new Book();
        BookDto expected = new BookDto();
        //When
        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));
        doNothing().when(bookMapper).updateBook(requestDto, book);
        when(bookRepository.save(book)).thenReturn(book);
        when(bookMapper.toDto(book)).thenReturn(expected);
        BookDto actual = bookService.update(bookId, requestDto);
        //Then
        assertEquals(expected, actual);
        verify(bookRepository, Mockito.times(1)).findById(bookId);
        verify(bookMapper, Mockito.times(1)).updateBook(requestDto, book);
        verify(bookMapper, Mockito.times(1)).toDto(book);
    }

    @Test
    @DisplayName("Find all books by category id")
    void findAllByCategoryId_returnBookDtoList() {
        //Given
        Long categoryId = 1L;
        Book firstBook = new Book();
        Book secondBook = new Book();
        List<Book> bookPage = List.of(firstBook, secondBook);
        Pageable pageable = Pageable.ofSize(1);

        BookDtoWithoutCategoryIds firstBookDto = new BookDtoWithoutCategoryIds();
        BookDtoWithoutCategoryIds secondBookDto = new BookDtoWithoutCategoryIds();
        final List<BookDtoWithoutCategoryIds> expected = List.of(firstBookDto, secondBookDto);
        //When
        when(bookMapper.toDtoWithoutCategoryIds(firstBook)).thenReturn(firstBookDto);
        when(bookMapper.toDtoWithoutCategoryIds(secondBook)).thenReturn(secondBookDto);
        when(bookRepository.findAllByCategoryId(categoryId, pageable)).thenReturn(bookPage);
        List<BookDtoWithoutCategoryIds> actual = bookService.findAllByCategoryId(categoryId,
                pageable);
        //Then
        assertEquals(expected, actual);
        verify(bookRepository, Mockito.times(1)).findAllByCategoryId(categoryId, pageable);
        verify(bookMapper, Mockito.times(1)).toDtoWithoutCategoryIds(firstBook);
        verify(bookMapper, Mockito.times(1)).toDtoWithoutCategoryIds(secondBook);
    }
}
