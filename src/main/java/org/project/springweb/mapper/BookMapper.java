package org.project.springweb.mapper;

import org.mapstruct.MappingTarget;
import org.project.springweb.config.MapperConfig;
import org.project.springweb.dto.BookDto;
import org.project.springweb.dto.CreateBookRequestDto;
import org.project.springweb.model.Book;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface BookMapper {
    BookDto toDto(Book book);

    Book toModel(CreateBookRequestDto requestDto);

    void updateBook(BookDto bookDto, @MappingTarget Book book);
}
