package org.project.springweb.repository.book;

import lombok.RequiredArgsConstructor;
import org.project.springweb.dto.book.BookDto;
import org.project.springweb.dto.book.BookSearchParametersDto;
import org.project.springweb.repository.SpecificationBuilder;
import org.project.springweb.repository.SpecificationProviderManager;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookSpecificationBuilder implements SpecificationBuilder<BookDto> {
    private static final String AUTHOR_KEY = "author";
    private static final String TITLE_KEY = "title";
    private final SpecificationProviderManager<BookDto> bookSpecificationProviderManager;

    @Override
    public Specification<BookDto> build(BookSearchParametersDto searchParametersDto) {
        Specification<BookDto> spec = Specification.where(null);
        if (searchParametersDto.authors() != null && searchParametersDto.authors().length > 0) {
            spec = spec.and(bookSpecificationProviderManager
                    .getSpecificationProvider(AUTHOR_KEY)
                    .getSpecification(searchParametersDto.authors()));
        }
        if (searchParametersDto.titles() != null && searchParametersDto.titles().length > 0) {
            spec = spec.and(bookSpecificationProviderManager
                    .getSpecificationProvider(TITLE_KEY)
                    .getSpecification(searchParametersDto.titles()));
        }
        return spec;
    }
}
