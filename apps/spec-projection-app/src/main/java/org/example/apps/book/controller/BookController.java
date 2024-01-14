package org.example.apps.book.controller;

import lombok.RequiredArgsConstructor;
import org.example.apps.book.dto.BookCreateRequest;
import org.example.apps.book.dto.BookResponse;
import org.example.apps.book.dto.BookSearchRequest;
import org.example.apps.book.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * @author rival
 * @since 2024-01-04
 */

@RestController
@RequestMapping("books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping("")
    public void createBook(@RequestBody BookCreateRequest bookCreateRequest){
        bookService.createBook(bookCreateRequest);
    }

    @GetMapping("/{id}")
    public BookResponse getBookById(@PathVariable(name="id") Long id){
        return bookService.findBookById(id);
    }

    @GetMapping("")
    public ResponseEntity<?> getBooks(BookSearchRequest request){
        var body = new HashMap<String, Object>();

        body.put("books", bookService.findBooks(request));



        return ResponseEntity.ok(body);
    }

}
