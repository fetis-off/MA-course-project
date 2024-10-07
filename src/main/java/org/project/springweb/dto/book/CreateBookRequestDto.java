package org.project.springweb.dto.book;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.project.springweb.validation.book.Author;
import org.project.springweb.validation.book.Isbn;
import java.math.BigDecimal;

@Data
public class CreateBookRequestDto {
    @NotBlank
    private String title;
    @Author
    private String author;
    @Isbn
    private String isbn;
    @NotBlank
    @Min(value = 0)
    private BigDecimal price;
    private String description;
    private String coverImage;
}
