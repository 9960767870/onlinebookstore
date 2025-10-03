package com.onlinebookstore.repository;

import com.onlinebookstore.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

   List<Book> findByCategory(String category);

}
