package mate.academy.springweb.mapper;

import mate.academy.springweb.config.MapperConfig;
import mate.academy.springweb.dto.BookDto;
import mate.academy.springweb.dto.CreateBookRequestDto;
import mate.academy.springweb.model.Book;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface BookMapper {
    BookDto toDto(Book book);

    Book toModel(CreateBookRequestDto requestDto);
}
