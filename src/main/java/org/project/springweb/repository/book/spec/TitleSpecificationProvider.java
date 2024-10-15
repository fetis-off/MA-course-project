package org.project.springweb.repository.book.spec;

import java.util.Arrays;
import org.project.springweb.dto.book.BookDto;
import org.project.springweb.repository.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class TitleSpecificationProvider implements SpecificationProvider<BookDto> {
    private static final String FIELD_NAME = "title";

    @Override
    public String getKey() {
        return FIELD_NAME;
    }

    @Override
    public Specification<BookDto> getSpecification(String[] params) {
        return (root, query, criteriaBuilder) -> root.get(FIELD_NAME)
                .in(Arrays.stream(params).toArray());
    }
}
