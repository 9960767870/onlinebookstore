package com.onlinebookstore.Services;

import com.onlinebookstore.entity.Book;
import com.onlinebookstore.entity.BookDTO;
import com.onlinebookstore.exceptionhandling.CustomException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {
    List<BookDTO> getAllBooks();
    BookDTO createBook(Book book);
    String deleteBook(int id);
    BookDTO updateBook(Book book) throws CustomException;
    BookDTO getBookById(int id) throws CustomException;
    List<BookDTO> getCategoryByBook(String category);
}
