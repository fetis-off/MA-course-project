package org.project.springweb.repository.book;

import org.project.springweb.dto.BookDto;
import org.project.springweb.repository.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import java.util.Arrays;

@Component
public class AuthorSpecificationProvider implements SpecificationProvider<BookDto> {

    @Override
    public String getKey() {
        return "author";
    }

    @Override
    public Specification<BookDto> getSpecification(String[] params) {
        return (root, query, criteriaBuilder) -> root.get("author").in(Arrays.stream(params).toArray());
    }
}
