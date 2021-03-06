package com.example.demo.controller;

import com.example.demo.model.Book;
import com.example.demo.model.BookRequest;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

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

    @GetMapping()
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @Valid @RequestBody BookRequest bookRequest) {
        return ResponseEntity.ok(bookService.updateBook(id, bookRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable("id") Long id) {
        bookService.deleteBookById(id);
        return ResponseEntity.ok().build();
    }

    private Long saveBook(BookRequest bookrequest) {
        return bookService.createNewBook(bookrequest);
    }

    @GetMapping("/sort")
    private ResponseEntity<List<Book>> getBooksWithSort(@RequestParam String field) {
        return ResponseEntity.ok(bookService.findBooksWithSorting(field));
    }

    @GetMapping("/pagination/{offset}/{pageSize}")
    private ResponseEntity<Page<Book>> getBooksWithPagination(@PathVariable int offset, @PathVariable int pageSize) {
        return ResponseEntity.ok(bookService.findBooksWithPagination(offset, pageSize));
    }

    @GetMapping("/paginationAndSort/{offset}/{pageSize}")
    private ResponseEntity<Page<Book>> getBooksWithPaginationAndSort(@PathVariable int offset, @PathVariable int pageSize, @RequestParam String field, @RequestParam String order) {
        return ResponseEntity.ok(bookService.findBooksWithPaginationAndSorting(offset, pageSize, field, order));
    }
}
