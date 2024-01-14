package org.example.apps.book.service;

import org.example.apps.book.dto.BookCreateRequest;
import org.example.apps.book.dto.BookResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author rival
 * @since 2024-01-04
 */

@SpringBootTest
class BookServiceTest {
    @Autowired
    private BookService bookService;

    @Test
    public void createTest(){
        BookCreateRequest bookCreateRequest = new BookCreateRequest();
        bookCreateRequest.setTitle("New Book");
        var tags = new HashSet<String>();
        tags.add("Spring");
        tags.add("Jpa");
        bookCreateRequest.setKeywordValues(tags);
        BookResponse createdBook = bookService.createBook(bookCreateRequest);

        assertEquals(2,createdBook.getKeywordValues().size());
    }
    
}