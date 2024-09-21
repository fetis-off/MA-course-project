package org.project.springweb.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.project.springweb.validation.Author;
import org.project.springweb.validation.Isbn;

import java.math.BigDecimal;

@Data
public class CreateBookRequestDto {
    @NotNull
    private String title;
    @Author
    private String author;
    @Isbn
    private String isbn;
    @NotNull
    @Min(value = 0)
    private BigDecimal price;
    private String description;
    private String coverImage;
}
