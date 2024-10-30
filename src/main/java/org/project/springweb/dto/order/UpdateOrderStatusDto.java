package org.project.springweb.dto.order;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.project.springweb.model.Status;

@Data
public class UpdateOrderStatusDto {
    @NotNull
    private Status status;
}
