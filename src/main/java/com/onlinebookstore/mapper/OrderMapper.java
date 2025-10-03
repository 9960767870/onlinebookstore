package com.onlinebookstore.mapper;

import com.onlinebookstore.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);
    @Mapping(source = "order.orderId", target = "orderId")
    @Mapping(source = "order.orderItems", target = "orderItems")
    @Mapping(source = "order.user.userId", target = "userId")
    OrderDTO toDTO(Order order);

    @Mapping(source = "orderItem.book.id", target = "bookId")
    @Mapping(source = "orderItem.book.title", target = "bookTitle")
    OrderItemDTO toDTO(OrderItems orderItem);

    List<OrderDTO> toDTO(List<Order> orders);

    List<OrderItemDTO> toDTOOrderItems(List<OrderItems> orderItems);
}
