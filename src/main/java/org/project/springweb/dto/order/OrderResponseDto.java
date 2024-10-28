package org.project.springweb.dto.order;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import lombok.Data;
import org.project.springweb.model.OrderItem;
import org.project.springweb.model.Status;

@Data
public class OrderResponseDto {
    private Long Id;
    private Long userId;
    private Set<OrderItem> orderItemsDtos;
    private LocalDateTime orderDate;
    private BigDecimal total;
    private Status status;
}
