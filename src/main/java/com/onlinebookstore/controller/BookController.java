package com.onlinebookstore.controller;

import com.onlinebookstore.Services.BookService;
import com.onlinebookstore.entity.Book;
import com.onlinebookstore.entity.BookDTO;
import com.onlinebookstore.exceptionhandling.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {


    @Autowired
    private BookService bookService;

    @GetMapping("/getallbooks")
    public ResponseEntity<List<BookDTO>> getBooks() {

        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping("/addbook")
    public ResponseEntity<BookDTO> addBook(@RequestBody Book book) {
        return ResponseEntity.ok(bookService.createBook(book));
    }

    @PreAuthorize("hasAuthority('Admin')")
    @PutMapping("/updatebook")
    public ResponseEntity<BookDTO> uptdateBook(@RequestBody Book book) throws CustomException {
        return ResponseEntity.ok(bookService.updateBook(book));
    }

    @GetMapping("/getbookbyid/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable int id) throws CustomException {
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @GetMapping("/getcategorybybook")
    public ResponseEntity<List<BookDTO>> getCategoryByBook(@RequestParam String category) {
        return ResponseEntity.ok(bookService.getCategoryByBook(category));
    }

    @PreAuthorize("hasAuthority('Admin')")
    @DeleteMapping("/deletebook/{id}")
    public ResponseEntity<String> deleteBookById(@PathVariable int id) {
        return ResponseEntity.ok(bookService.deleteBook(id));    }


}
