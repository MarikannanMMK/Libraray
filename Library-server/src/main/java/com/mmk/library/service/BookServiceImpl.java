package com.mmk.library.service;

import com.mmk.library.entity.Book;
import com.mmk.library.exception.BookNotFoundException;
import com.mmk.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book saveBook(Book book) {

        return bookRepository.save(book);
    }

    @Override
    public void deleteBook(long bookId) {
        Book book = findBookById(bookId);
        bookRepository.deleteById(book.getBookId());
    }


    @Override
    public Book findBookById(long bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException("Book With Given ID :" + bookId + " Not Available"));
    }

    @Override
    public List<Book> getAllBooks() {

        return bookRepository.findAll();
    }

    @Override
    public List<Book> getSearchByKeyword(String keyWord) {

        return bookRepository.findBySearchKeyword(keyWord);
    }

    @Override
    public Page<Book> getAllBooksWithPagination(int offset, int pagSize) {
        Page<Book> books = bookRepository.findAll(PageRequest.of(offset,pagSize));
        return books;
    }

    @Override
    public Book updateBook(Book book) {
        bookRepository.findById(book.getBookId()).orElseThrow(
                () -> new BookNotFoundException("Book With Given ID :" + book.getBookId() + " Not Available"));
        Book book1 = bookRepository.findById(book.getBookId()).get();
        book1.setTitle(book.getTitle());
        book1.setAuthorName(book.getAuthorName());
        book1.setPublicationDate(book.getPublicationDate());
        book1.setISBN(book.getISBN());
        return bookRepository.save(book1);
    }

}
