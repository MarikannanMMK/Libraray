package com.mmk.library.service;

import com.mmk.library.entity.Book;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BookService {

    Book saveBook(Book book);

    void deleteBook(long bookId);

    Book updateBook(Book book);

    Book findBookById(long bookId);

    List<Book> getAllBooks();

    Page<Book> getAllBooksWithPagination(int offset, int pageSize);

    List<Book> getSearchByKeyword(String keyWord);
}
