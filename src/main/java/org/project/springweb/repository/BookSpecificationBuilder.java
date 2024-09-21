package org.project.springweb.repository;

import lombok.RequiredArgsConstructor;
import org.project.springweb.dto.BookDto;
import org.project.springweb.dto.BookSearchParametersDto;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookSpecificationBuilder implements SpecificationBuilder<BookDto> {
    private final SpecificationProviderManager<BookDto> bookSpecificationProviderManager;

    @Override
    public Specification<BookDto> build(BookSearchParametersDto searchParametersDto) {
        Specification<BookDto> spec = Specification.where(null);
        if (searchParametersDto.authors() != null && searchParametersDto.authors().length > 0) {
            spec = spec.and(bookSpecificationProviderManager
                    .getSpecificationProvider("author")
                    .getSpecification(searchParametersDto.authors()));
        }
        if (searchParametersDto.titles() != null && searchParametersDto.titles().length > 0) {
            spec = spec.and(bookSpecificationProviderManager
                    .getSpecificationProvider("title")
                    .getSpecification(searchParametersDto.titles()));
        }
        return spec;
    }
}
