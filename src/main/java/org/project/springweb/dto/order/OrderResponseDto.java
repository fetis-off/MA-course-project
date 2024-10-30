package org.project.springweb.dto.order;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import lombok.Data;
import org.project.springweb.dto.orderitem.OrderItemResponseDto;
import org.project.springweb.model.Status;

@Data
public class OrderResponseDto {
    private Long id;
    private Long userId;
    private Set<OrderItemResponseDto> orderItemsDtos;
    private LocalDateTime orderDate;
    private BigDecimal total;
    private Status status;
}
