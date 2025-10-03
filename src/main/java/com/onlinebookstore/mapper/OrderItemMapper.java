package com.onlinebookstore.mapper;

import com.onlinebookstore.entity.Book;
import com.onlinebookstore.entity.BookDTO;
import com.onlinebookstore.entity.OrderItemDTO;
import com.onlinebookstore.entity.OrderItems;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {
    OrderItemMapper INSTANCE = Mappers.getMapper(OrderItemMapper.class);

    OrderItemDTO toDTO(OrderItems orderItems);

    OrderItems toEntity(OrderItemDTO orderItemDTO);
}
