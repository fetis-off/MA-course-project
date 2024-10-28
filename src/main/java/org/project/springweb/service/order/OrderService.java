package org.project.springweb.service.order;

import java.util.List;
import org.project.springweb.dto.order.CreateOrderRequestDto;
import org.project.springweb.dto.order.OrderResponseDto;
import org.project.springweb.dto.order.UpdateOrderStatusDto;
import org.project.springweb.dto.orderitem.OrderItemResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    OrderResponseDto createOrder(CreateOrderRequestDto requestDto, Long userId);

    Page<OrderResponseDto> getAllOrdersByUserId(Pageable pageable, Long userId);

    OrderResponseDto updateOrderStatus(UpdateOrderStatusDto requestDto, Long orderId);

    List<OrderItemResponseDto> getAllItemsInSpecificOrder(Long orderId, Long userId);

    OrderItemResponseDto getSpecificOrderItem(Long orderId, Long userId, Long orderItemId);
}
