package com.example.demo.controller;

import com.example.demo.model.Author;
import com.example.demo.model.Book;
import com.example.demo.model.BookRequest;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping()
    public ResponseEntity<Void> createNewBook(@Valid @RequestBody BookRequest bookrequest, UriComponentsBuilder uriComponentsBuilder) {
        Long id = saveBook(bookrequest);
        UriComponents uriComponents = uriComponentsBuilder.path("api/books/{id}").buildAndExpand(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponents.toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    private Long saveBook(BookRequest bookrequest) {
        return bookService.createNewBook(bookrequest);
    }
}
