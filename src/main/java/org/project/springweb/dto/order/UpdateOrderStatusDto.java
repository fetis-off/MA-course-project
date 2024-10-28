package org.project.springweb.dto.order;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.project.springweb.model.Status;

@Data
public class UpdateOrderStatusDto {
    @NotBlank
    private Status status;
}
