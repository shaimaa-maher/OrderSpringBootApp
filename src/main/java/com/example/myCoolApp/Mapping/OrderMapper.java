package com.example.myCoolApp.Mapping;

import com.example.myCoolApp.Entities.Order;
import com.example.myCoolApp.Entities.OrderItem;
import com.example.myCoolApp.Model.Entities.Dtos.OrderDto;
import com.example.myCoolApp.Model.Entities.Dtos.OrderItemDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(target = "orderItemsList", source = "orderItemsList")
    Order toEntity(OrderDto dto);

    @Mapping(target = "orderItemsList", source = "orderItemsList")
    OrderDto toDTO(Order entity);

    OrderItem toEntity(OrderItemDto dto);
    OrderItemDto toDTO(OrderItem entity);

    List<OrderItem> toEntity(List<OrderItemDto> dtoList);
    List<OrderItemDto> toDTO(List<OrderItem> entityList);
}
