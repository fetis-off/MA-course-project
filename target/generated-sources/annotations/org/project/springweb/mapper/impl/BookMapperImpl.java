package org.project.springweb.mapper.impl;

import javax.annotation.processing.Generated;
import org.project.springweb.dto.book.BookDto;
import org.project.springweb.dto.book.CreateBookRequestDto;
import org.project.springweb.mapper.BookMapper;
import org.project.springweb.model.Book;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-03T15:55:14+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
@Component
public class BookMapperImpl implements BookMapper {

    @Override
    public BookDto toDto(Book book) {
        if ( book == null ) {
            return null;
        }

        BookDto bookDto = new BookDto();

        if ( book.getId() != null ) {
            bookDto.setId( book.getId() );
        }
        if ( book.getTitle() != null ) {
            bookDto.setTitle( book.getTitle() );
        }
        if ( book.getAuthor() != null ) {
            bookDto.setAuthor( book.getAuthor() );
        }
        if ( book.getIsbn() != null ) {
            bookDto.setIsbn( book.getIsbn() );
        }
        if ( book.getPrice() != null ) {
            bookDto.setPrice( book.getPrice() );
        }
        if ( book.getDescription() != null ) {
            bookDto.setDescription( book.getDescription() );
        }
        if ( book.getCoverImage() != null ) {
            bookDto.setCoverImage( book.getCoverImage() );
        }

        return bookDto;
    }

    @Override
    public Book toModel(CreateBookRequestDto requestDto) {
        if ( requestDto == null ) {
            return null;
        }

        Book book = new Book();

        if ( requestDto.getTitle() != null ) {
            book.setTitle( requestDto.getTitle() );
        }
        if ( requestDto.getAuthor() != null ) {
            book.setAuthor( requestDto.getAuthor() );
        }
        if ( requestDto.getIsbn() != null ) {
            book.setIsbn( requestDto.getIsbn() );
        }
        if ( requestDto.getPrice() != null ) {
            book.setPrice( requestDto.getPrice() );
        }
        if ( requestDto.getDescription() != null ) {
            book.setDescription( requestDto.getDescription() );
        }
        if ( requestDto.getCoverImage() != null ) {
            book.setCoverImage( requestDto.getCoverImage() );
        }

        return book;
    }

    @Override
    public void updateBook(CreateBookRequestDto bookDto, Book book) {
        if ( bookDto == null ) {
            return;
        }

        if ( bookDto.getTitle() != null ) {
            book.setTitle( bookDto.getTitle() );
        }
        else {
            book.setTitle( null );
        }
        if ( bookDto.getAuthor() != null ) {
            book.setAuthor( bookDto.getAuthor() );
        }
        else {
            book.setAuthor( null );
        }
        if ( bookDto.getIsbn() != null ) {
            book.setIsbn( bookDto.getIsbn() );
        }
        else {
            book.setIsbn( null );
        }
        if ( bookDto.getPrice() != null ) {
            book.setPrice( bookDto.getPrice() );
        }
        else {
            book.setPrice( null );
        }
        if ( bookDto.getDescription() != null ) {
            book.setDescription( bookDto.getDescription() );
        }
        else {
            book.setDescription( null );
        }
        if ( bookDto.getCoverImage() != null ) {
            book.setCoverImage( bookDto.getCoverImage() );
        }
        else {
            book.setCoverImage( null );
        }
    }
}
