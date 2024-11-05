package org.project.springweb.util;

import java.math.BigDecimal;
import java.util.Set;
import org.project.springweb.dto.book.CreateBookRequestDto;
import org.project.springweb.dto.category.CategoryDto;
import org.project.springweb.dto.category.CreateCategoryRequestDto;

public final class TestUtil {
    private TestUtil() {
    }

    public static String getFirstIsbn() {
        return "0000000000001";
    }

    public static String getSecondIsbn() {
        return "0000000000002";
    }

    public static CreateBookRequestDto createBookRequestDto() {
        return new CreateBookRequestDto()
                .setTitle("C++ Book")
                .setAuthor("Author First")
                .setIsbn(getFirstIsbn())
                .setPrice(BigDecimal.valueOf(40))
                .setCategoryIds(Set.of(1L));
    }

    public static CreateCategoryRequestDto createCategoryRequestDto() {
        return new CreateCategoryRequestDto()
                .setName("Adventure")
                .setDescription("Adventure books");
    }

    public static CategoryDto createCategoryResponseDto() {
        CreateCategoryRequestDto requestDto = createCategoryRequestDto();
        return new CategoryDto()
                .setName(requestDto.getName())
                .setDescription(requestDto.getDescription());
    }

    public static CategoryDto updateCategoryResponseDto() {
        CreateCategoryRequestDto requestDto = updateCategoryRequestDto();
        return new CategoryDto()
                .setName(requestDto.getName())
                .setDescription(requestDto.getDescription());
    }

    public static CreateCategoryRequestDto updateCategoryRequestDto() {
        return new CreateCategoryRequestDto()
                .setName("Adventure")
                .setDescription("Adventure books are all about exciting journeys "
                        + "and thrilling experiences!");
    }
}
