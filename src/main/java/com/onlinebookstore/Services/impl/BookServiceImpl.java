package com.onlinebookstore.Services.impl;

import com.onlinebookstore.Services.BookService;
import com.onlinebookstore.entity.Book;
import com.onlinebookstore.entity.BookDTO;
import com.onlinebookstore.exceptionhandling.CustomException;
import com.onlinebookstore.mapper.BookMapper;
import com.onlinebookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BookMapper bookMapper;

    @Override
    public List<BookDTO> getAllBooks() {
        List<Book> all = bookRepository.findAll();
        return all.stream()
                .map(bookMapper::toDTO)  // Map one by one
                .collect(Collectors.toList());

    }

    @Override
    public BookDTO createBook(Book book) {
        book = bookRepository.save(book);
        return bookMapper.toDTO(book);
    }

    @Override
    public String deleteBook(int id) {
            bookRepository.deleteById(id);
            return "Book Deleted Successfully";
    }

    @Override
    public BookDTO updateBook(Book book) throws CustomException {
        Optional<Book> byId = bookRepository.findById(book.getId());
        if (byId.isPresent()) {
            Book existingBook = byId.get();
            // Update fields
            existingBook.setId(book.getId());
            existingBook.setTitle(book.getTitle());
            existingBook.setAuthor(book.getAuthor());
            existingBook.setPrice(book.getPrice());
            existingBook.setCategory(book.getCategory());
            Book saved = bookRepository.save(existingBook);
            return bookMapper.toDTO(saved);
        }else{
            throw new CustomException("Book not found");
        }
    }

    @Override
    public BookDTO getBookById(int id) throws CustomException {
        Book book = bookRepository.findById(id).orElseThrow(() -> new CustomException("Book Not Found"));
        return bookMapper.toDTO(book);
    }

    @Override
    public List<BookDTO> getCategoryByBook(String category) {

        List<Book> byCategory = bookRepository.findByCategory(category);
        return byCategory.stream()
                .map(bookMapper::toDTO)  // Map one by one
                .collect(Collectors.toList());
    }
}
