package org.project.springweb.repository;

import lombok.RequiredArgsConstructor;
import org.project.springweb.dto.BookDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BookSpecificationProviderManager implements SpecificationProviderManager<BookDto> {
    private final List<SpecificationProvider<BookDto>> bookSpecificationProviders;

    @Override
    public SpecificationProvider<BookDto> getSpecificationProvider(String key) {
        return bookSpecificationProviders.stream()
                .filter(p -> p.getKey().equals(key))
                .findFirst()
                .orElseThrow(
                        () -> new RuntimeException("No specification provider found for key: "
                        + key));

    }
}
