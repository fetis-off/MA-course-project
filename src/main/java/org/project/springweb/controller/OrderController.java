package org.project.springweb.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.project.springweb.dto.order.CreateOrderRequestDto;
import org.project.springweb.dto.order.OrderResponseDto;
import org.project.springweb.dto.order.UpdateOrderStatusDto;
import org.project.springweb.dto.orderitem.OrderItemResponseDto;
import org.project.springweb.model.User;
import org.project.springweb.service.order.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Books store", description = "Endpoints for managing orders")
@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Create a new order",
            description = "Create a new order with current shopping cart")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public OrderResponseDto placeAnOrder(@RequestBody @Valid CreateOrderRequestDto requestDto,
                                         @AuthenticationPrincipal User user) {
        return orderService.createOrder(requestDto, user.getId());
    }

    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Get all orders",
            description = "Get all orders that belong to current user")
    @GetMapping
    public List<OrderResponseDto> getOrders(@AuthenticationPrincipal User user) {
        return orderService.getAllOrdersByUserId(user.getId());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Update an order", description = "Update status of specific order")
    @PatchMapping("/{id}")
    public OrderResponseDto updateOrderStatus(@RequestBody UpdateOrderStatusDto requestDto,
                                              @PathVariable Long id) {
        return orderService.updateOrderStatus(requestDto, id);
    }

    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Get order items", description = "Get order items from specific order")
    @GetMapping("/{orderId}/items")
    public List<OrderItemResponseDto> getOrderItemsForSpecificOrder(@PathVariable Long orderId,
             @AuthenticationPrincipal User user) {
        return orderService.getAllItemsInSpecificOrder(orderId, user.getId());
    }

    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Get order item",
            description = "Get specific order item from specific order")
    @GetMapping("/{orderId}/items/{itemId}")
    public OrderItemResponseDto getOrderItemByOrderItemId(@PathVariable Long orderId,
                                                          @AuthenticationPrincipal User user,
                                                          @PathVariable Long itemId) {
        return orderService.getSpecificOrderItem(orderId, user.getId(), itemId);
    }
}
