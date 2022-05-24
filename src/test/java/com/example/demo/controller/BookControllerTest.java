package com.example.demo.controller;

import com.example.demo.model.BookRequest;
import com.example.demo.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@ExtendWith(SpringExtension.class)
@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private BookService bookService;

    @Captor
    private ArgumentCaptor<BookRequest> bookRequestArgumentCaptor;

    @Test
    public void shouldCreateNewBook() throws Exception {
        String sampleAuthor = "J. K. Rowling";
        String sampleTitle = "Harry Potter and the Sorcerer's Stone";
        BookRequest bookRequest = new BookRequest();
        bookRequest.setAuthor(sampleAuthor);
        bookRequest.setTitle(sampleTitle);

        when(bookService.createNewBook(bookRequestArgumentCaptor.capture())).thenReturn(1L);

        this.mockMvc
                .perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bookRequest)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(header().string("Location", "http://localhost/api/books/1"));

        assertThat(bookRequestArgumentCaptor.getValue().getAuthor(), is(sampleAuthor));
        assertThat(bookRequestArgumentCaptor.getValue().getTitle(), is(sampleTitle));

    }

}