package com.example.demo.service;

import com.example.demo.model.Author;
import com.example.demo.model.Book;
import com.example.demo.model.BookRequest;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    public Long createNewBook(BookRequest bookRequest){
        List<Author> fetchedAuthors = authorRepository.findByNameIgnoreCase(bookRequest.getAuthor());

        Book book = new Book();
        if(CollectionUtils.isEmpty(fetchedAuthors)){
            book.setAuthor(authorRepository.save(buildAuthor(bookRequest.getAuthor())));
        }else{
            book.setAuthor(fetchedAuthors.get(0)); //since this is just a demo, no further validation
        }

        book.setTitle(bookRequest.getTitle());
        Book newBook = bookRepository.save(book);

        return newBook.getId();
    }

    private Author buildAuthor(String authorName) {
        return Author.builder().name(authorName).build();
    }
}
