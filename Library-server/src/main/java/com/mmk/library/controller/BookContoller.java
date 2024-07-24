package com.mmk.library.controller;

import com.mmk.library.entity.Book;
import com.mmk.library.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookContoller {

    @Autowired
   private BookService bookService;

    @GetMapping(value = "/test")
    public String demo(){
        return "Welcome";
    }

    @PostMapping(value = "/create")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Book> saveBook(@Valid @RequestBody Book book) {
        Book savedBook = bookService.saveBook(book);
        return new ResponseEntity<Book>(savedBook, HttpStatus.CREATED);
    }
    @GetMapping(value = "/allBooks")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER') ")
    public ResponseEntity<List<Book>> getAllBooks(){
        List<Book> bookList = bookService.getAllBooks();
        return new ResponseEntity<List<Book>>(bookList,HttpStatus.OK);
    }

    @DeleteMapping(value = "delete/{bookId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "This Method is for Delete Book object By Providing Book ID as input")
    public ResponseEntity<String> deleteBookById(@PathVariable Integer bookId) {
        bookService.deleteBook(bookId);
        String msg = "Book With ID :" + bookId + " Deleted Successfully";
        return new ResponseEntity<String>(msg, HttpStatus.OK);
    }
    @PutMapping(value = "/update")
    @PreAuthorize("hasAuthority('ADMIN')|| hasAuthority('USER')")
    @Operation(summary = "This Method is for update the Book Details and this method returns Updated Book Object")
    public ResponseEntity<Book> updateBook(@Valid @RequestBody Book Book) {
        Book updatedBook = bookService.updateBook(Book);
        return new ResponseEntity<Book>(updatedBook, HttpStatus.ACCEPTED);
    }

    @GetMapping(value = "/fetchWithPagination/{offset}/{pageSize}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "This Method is for fetching all the  Book Details with Pagination Details")
    public ResponseEntity<Page<Book>> getAllWithPagination(@PathVariable("offset")  int offset , @PathVariable("pageSize") int pagSize){
        Page<Book> books = bookService.getAllBookswithPagination(offset,pagSize);
        return new ResponseEntity<>(books,HttpStatus.OK);

    }

}
