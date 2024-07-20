package com.mmk.library.service;

import com.mmk.library.entity.Book;
import com.mmk.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService{

    @Autowired
    private BookRepository bookRepository;
    @Override
    public Book saveBook(Book book) {

        return bookRepository.save(book);
    }
}
