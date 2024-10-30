package org.project.springweb.service.order;

import java.util.List;
import org.project.springweb.dto.order.CreateOrderRequestDto;
import org.project.springweb.dto.order.OrderResponseDto;
import org.project.springweb.dto.order.UpdateOrderStatusDto;
import org.project.springweb.dto.orderitem.OrderItemResponseDto;

public interface OrderService {
    OrderResponseDto createOrder(CreateOrderRequestDto requestDto, Long userId);

    List<OrderResponseDto> getAllOrdersByUserId(Long userId);

    OrderResponseDto updateOrderStatus(UpdateOrderStatusDto requestDto, Long orderId);

    List<OrderItemResponseDto> getAllItemsInSpecificOrder(Long orderId, Long userId);

    OrderItemResponseDto getSpecificOrderItem(Long orderId, Long userId, Long orderItemId);
}
