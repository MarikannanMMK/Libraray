package com.mmk.library.controller;

import com.mmk.library.entity.Book;
import com.mmk.library.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.format.DecimalStyle;

@RestController
@RequestMapping("/book")
public class BookContoller {

    @Autowired
   private BookService bookService;

    @GetMapping("/test")
    public String demo(){
        return "Welcome";
    }
    @PostMapping(value = "/saveBook")
    public ResponseEntity<Book> saveBook(@Valid @RequestBody Book book) {
        Book savedBook = bookService.saveBook(book);
        return new ResponseEntity<Book>(savedBook, HttpStatus.CREATED);
    }
}
