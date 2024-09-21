package org.project.springweb.repository.book;

import org.project.springweb.dto.BookDto;
import org.project.springweb.repository.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class TitleSpecificationProvider implements SpecificationProvider<BookDto> {

    @Override
    public String getKey() {
        return "title";
    }

    @Override
    public Specification<BookDto> getSpecification(String[] params) {
        return (root, query, criteriaBuilder) -> root.get("title").in(Arrays.stream(params).toArray());
    }
}
