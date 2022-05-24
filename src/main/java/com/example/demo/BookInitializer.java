package com.example.demo;

import com.example.demo.model.Book;
//import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.BookRepository;
import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BookInitializer implements CommandLineRunner {

    @Autowired
    private BookRepository bookRepository;

//    @Autowired
//    private AuthorRepository authorRepository;
//

    @Override
    public void run(String... args) throws Exception {
        log.info("Starting to initialize sample data ...");

        Faker faker = new Faker();
        for (int i = 0; i < 10; i++) {
            Book book = new Book();
            book.setTitle(faker.book().title());
//            book.setAuthor(buildFakeAuthor());
            book.setAuthor(faker.book().author());
            bookRepository.save(book);
        }
        log.info("... finished with data initialization");
//        log.info(bookRepository.findAll());
    }

//    private Author buildFakeAuthor() {
//        Faker faker = new Faker();
//        Author author = new Author();
//        author.setName(faker.name().fullName());
//        return authorRepository.save(author);
//    }
}
