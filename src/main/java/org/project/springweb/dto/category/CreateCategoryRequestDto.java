package org.project.springweb.dto.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CreateCategoryRequestDto {
    @NotBlank
    @Size(min = 5, max = 30)
    private String name;

    @Size(min = 10, max = 100)
    private String description;
}
