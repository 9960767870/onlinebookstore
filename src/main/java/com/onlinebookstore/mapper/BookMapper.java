package com.onlinebookstore.mapper;

import com.onlinebookstore.entity.Book;
import com.onlinebookstore.entity.BookDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);
    BookDTO toDTO(Book book);
    Book toEntity(BookDTO bookDTO);
    List<BookDTO> toDTO(List<Book> books);
}
