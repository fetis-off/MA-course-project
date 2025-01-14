package org.project.springweb.service.order;

import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.project.springweb.dto.order.CreateOrderRequestDto;
import org.project.springweb.dto.order.OrderResponseDto;
import org.project.springweb.dto.order.UpdateOrderStatusDto;
import org.project.springweb.dto.orderitem.OrderItemResponseDto;
import org.project.springweb.exception.DataProcessingException;
import org.project.springweb.exception.EntityNotFoundException;
import org.project.springweb.exception.OrderProcessingException;
import org.project.springweb.mapper.OrderItemMapper;
import org.project.springweb.mapper.OrderMapper;
import org.project.springweb.model.Order;
import org.project.springweb.model.OrderItem;
import org.project.springweb.model.ShoppingCart;
import org.project.springweb.model.Status;
import org.project.springweb.repository.order.OrderRepository;
import org.project.springweb.repository.orderitem.OrderItemRepository;
import org.project.springweb.repository.shoppingcart.ShoppingCartRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    private final OrderItemMapper orderItemMapper;
    private final OrderMapper orderMapper;
    private final OrderItemRepository orderItemRepository;

    @Transactional
    @Override
    public OrderResponseDto createOrder(CreateOrderRequestDto requestDto, Long userId) {
        ShoppingCart shoppingCart = shoppingCartRepository.findByUserId(userId).orElseThrow(
                () -> new EntityNotFoundException("Cannot find shopping cart for user "
                        + userId));
        if (shoppingCart.getCartItems().isEmpty()) {
            throw new OrderProcessingException("Shopping cart is empty, cannot create order");
        }
        Order order = fillOrder(shoppingCart, requestDto);
        orderRepository.save(order);
        shoppingCart.getCartItems().clear();
        shoppingCartRepository.save(shoppingCart);
        return orderMapper.toDto(order);
    }

    @Override
    public List<OrderResponseDto> getAllOrdersByUserId(Long userId) {
        List<Order> orders = orderRepository.findAllByUserId(userId);
        return orders.stream()
                .map(orderMapper::toDto)
                .toList();
    }

    @Override
    public OrderResponseDto updateOrderStatus(UpdateOrderStatusDto requestDto, Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new EntityNotFoundException("Cannot find order with id " + orderId)
        );

        if (!isValidStatus(requestDto.getStatus())) {
            throw new DataProcessingException("Invalid status "
                    + requestDto.getStatus());
        }
        order.setStatus(requestDto.getStatus());
        return orderMapper.toDto(orderRepository.save(order));
    }

    @Override
    public List<OrderItemResponseDto> getAllItemsInSpecificOrder(Long orderId, Long userId) {
        Order order = getOrderByIdAndUserId(orderId, userId);
        return order.getItems().stream()
                .map(orderItemMapper::toDto)
                .toList();
    }

    @Override
    public OrderItemResponseDto getSpecificOrderItem(Long orderId, Long userId, Long orderItemId) {
        OrderItem orderItem = orderItemRepository
                .findByIdAndOrderIdAndOrderUserId(orderItemId, orderId, userId)
                .orElseThrow(
                        () -> new EntityNotFoundException("Order item with id "
                                + orderItemId
                                + " not found")
                );
        return orderItemMapper.toDto(orderItem);
    }

    private Order fillOrder(ShoppingCart shoppingCart, CreateOrderRequestDto requestDto) {
        Order order = new Order();
        order.setUser(shoppingCart.getUser());
        order.setStatus(Status.PENDING);
        order.setOrderDate(LocalDateTime.now());
        order.setShippingAddress(requestDto.getShippingAddress());
        Set<OrderItem> orderItems = shoppingCart.getCartItems().stream()
                .map(cartItem -> {
                    OrderItem orderItem = orderItemMapper.toOrderItem(cartItem);
                    orderItem.setOrder(order);
                    return orderItem;
                })
                .collect(Collectors.toSet());
        order.setItems(orderItems);
        BigDecimal total = shoppingCart.getCartItems().stream()
                .map(cartItem -> cartItem.getBook().getPrice().multiply(
                        BigDecimal.valueOf(cartItem.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        order.setTotal(total);
        return order;
    }

    private Order getOrderByIdAndUserId(Long orderId, Long userId) {
        return orderRepository.findByIdAndUserId(orderId, userId)
                .orElseThrow(
                        () -> new EntityNotFoundException("Order with order id " + orderId
                                + " not found or doesn't belong to user with id " + userId)
                );
    }

    private boolean isValidStatus(Status status) {
        return Arrays.stream(Status.values())
                .anyMatch(s -> s.name().equalsIgnoreCase(status.name()));
    }
}
