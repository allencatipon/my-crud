package com.example.demo.repository;

import com.example.demo.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByTitleOrderByTitle(String title);
    List<Book> findByTitleOrderByTitleAsc(String title);

    Page<Book> findAllByTitleIgnoreCase(String title, Pageable pageable);
}