package org.project.springweb.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.project.springweb.config.MapperConfig;
import org.project.springweb.dto.order.OrderResponseDto;
import org.project.springweb.model.Order;

@Mapper(config = MapperConfig.class, uses = {OrderItemMapper.class})
public interface OrderMapper {
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "items", target = "orderItemsDtos",
            qualifiedByName = "toOrderItemDtoSet")
    OrderResponseDto toDto(Order order);
}
