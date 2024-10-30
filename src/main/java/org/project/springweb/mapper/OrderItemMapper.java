package org.project.springweb.mapper;

import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.project.springweb.config.MapperConfig;
import org.project.springweb.dto.orderitem.OrderItemResponseDto;
import org.project.springweb.model.CartItem;
import org.project.springweb.model.OrderItem;

@Mapper(config = MapperConfig.class)
public interface OrderItemMapper {
    @Mapping(source = "book.id", target = "bookId")
    OrderItemResponseDto toDto(OrderItem orderItem);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "book.price", target = "price")
    OrderItem toOrderItem(CartItem cartItem);

    @Named("toOrderItemDtoSet")
    default Set<OrderItemResponseDto> toOrderItemDtoSet(Set<OrderItem> items) {
        return items.stream()
                .map(this::toDto)
                .collect(Collectors.toSet());
    }
}
