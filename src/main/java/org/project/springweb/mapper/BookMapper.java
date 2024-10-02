package org.project.springweb.mapper;

import org.mapstruct.MappingTarget;
import org.project.springweb.config.MapperConfig;
import org.project.springweb.dto.book.BookDto;
import org.project.springweb.dto.book.CreateBookRequestDto;
import org.project.springweb.model.Book;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface BookMapper {
    BookDto toDto(Book book);

    Book toModel(CreateBookRequestDto requestDto);

    void updateBook(CreateBookRequestDto bookDto, @MappingTarget Book book);
}
