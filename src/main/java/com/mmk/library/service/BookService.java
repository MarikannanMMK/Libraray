package com.mmk.library.service;

import com.mmk.library.entity.Book;

import java.util.List;

public interface BookService {

    Book saveBook(Book book);

    void deleteBook(long bookId);

    Book updateBook(Book book);

    Book findBookById(long bookId);

    List<Book> getAllBooks();
}
