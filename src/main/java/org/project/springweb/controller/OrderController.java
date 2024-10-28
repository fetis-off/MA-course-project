package org.project.springweb.controller;

import java.util.List;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.project.springweb.dto.order.CreateOrderRequestDto;
import org.project.springweb.dto.order.OrderResponseDto;
import org.project.springweb.dto.order.UpdateOrderStatusDto;
import org.project.springweb.dto.orderitem.OrderItemResponseDto;
import org.project.springweb.model.User;
import org.project.springweb.service.order.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Books store", description = "Endpoints for managing orders")
@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    @PreAuthorize("hasRole('USER')")
    @PostMapping
    public OrderResponseDto placeAnOrder(@RequestBody @Valid CreateOrderRequestDto requestDto,
                                         @AuthenticationPrincipal User user) {
        return orderService.createOrder(requestDto, user.getId());
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping
    public Page<OrderResponseDto> getOrders(Pageable pageable, @AuthenticationPrincipal User user) {
        return orderService.getAllOrdersByUserId(pageable, user.getId());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public OrderResponseDto updateOrderStatus(@RequestBody @Valid UpdateOrderStatusDto requestDto,
                                              @PathVariable Long id) {
        return orderService.updateOrderStatus(requestDto, id);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{orderId}/items")
    public List<OrderItemResponseDto> getOrderItemsForSpecificOrder(@PathVariable Long orderId,
                                                                     @AuthenticationPrincipal User user) {
        return orderService.getAllItemsInSpecificOrder(orderId, user.getId());
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{id}/items/{id}")
    public OrderItemResponseDto getOrderItemByOrderItemId(@PathVariable Long orderId,
                                                   @AuthenticationPrincipal User user,
                                                          @PathVariable Long itemId) {
        return orderService.getSpecificOrderItem(orderId, user.getId(), itemId);
    }
}
