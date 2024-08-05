package com.mmk.library.serviceTest;

import com.mmk.library.entity.Book;
import com.mmk.library.exception.BookNotFoundException;
import com.mmk.library.repository.BookRepository;
import com.mmk.library.service.BookServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class bookServiceTests {

    @InjectMocks
    private BookServiceImpl bookServiceImpl;

    @Mock
    private BookRepository bookRepository;


    Book book = new Book();


    @BeforeEach
    public void setUp() {
        Date date = new Date();
        book.setBookId(2l);
        book.setTitle("Java");
        book.setAuthorName("Marikannan");
        book.setPublicationDate(date);
        book.setISBN(1234567890l);
        book.setAvailableStock(10);

    }

    @DisplayName("Junit test for saveBook Method")
    @Test
    public void givenBookObject_whenCreateBook_thenReturnSavedBook() throws Exception {

        Mockito.when(bookRepository.save(book)).thenReturn(book);

        System.out.println(bookRepository);
        System.out.println(bookServiceImpl);

        Book savedBook = bookServiceImpl.saveBook(book);

        System.out.println(savedBook);

        Assertions.assertThat(savedBook).isNotNull();

    }

    @DisplayName("JUnit test for getAllBooks method")
    @Test
    public void givenBookList_whenGetAllBooks_thenReturnBookList() {
        Book book1 = new Book();
        Date date = new Date();
        book1.setBookId(3l);
        book1.setTitle("React");
        book1.setAuthorName("Marikannan");
        book1.setPublicationDate(date);
        book1.setISBN(1234567890l);
        book1.setAvailableStock(10);

        Mockito.when(bookRepository.findAll()).thenReturn(List.of(book, book1));

        List<Book> bookList = bookServiceImpl.getAllBooks();

        Assertions.assertThat(bookList).isNotNull();
        Assertions.assertThat(bookList.size()).isEqualTo(2);
    }

    @DisplayName("JUnit test for getBookById method")
    @Test
    public void givenBookId_whenGetBookById_thenReturnBookObject() {
        Mockito.when(bookRepository.findById(2L)).thenReturn(Optional.of(book));

        Book savedBook = bookServiceImpl.findBookById(book.getBookId());

        Assertions.assertThat(savedBook).isNotNull();

    }

    @DisplayName("JUnit test for updateBook method")
    @Test
    public void givenBookObject_whenUpdateBook_thenReturnUpdatedBook() {

        Mockito.when(bookRepository.findById(2L)).thenReturn(Optional.of(book));
        //Mockito.when(bookRepository.findById(2L)).thenReturn(Optional.empty());
        book.setAuthorName("MMK");
        Mockito.when(bookRepository.save(book)).thenReturn(book);
        Book updatedBook = bookServiceImpl.updateBook(book);

        //org.junit.jupiter.api.Assertions.assertThrows(BookNotFoundException.class,()->bookServiceImpl.updateBook(book));
        Assertions.assertThat(updatedBook.getAuthorName()).isEqualTo("MMK");

    }


    @DisplayName("JUnit test for deleteBook method")
    @Test
    public void givenBookId_whenDeleteBook_thenNothing() {

        long bookId = 2L;

        //Mockito.when(bookRepository.findById(2L)).thenReturn(Optional.empty());
        //org.junit.jupiter.api.Assertions.assertThrows(BookNotFoundException.class,()->bookServiceImpl.deleteBook(bookId));
        Mockito.when(bookRepository.findById(2L)).thenReturn(Optional.of(book));
        Mockito.doNothing().when(bookRepository).deleteById(bookId);
        bookServiceImpl.deleteBook(bookId);
        Mockito.verify(bookRepository, Mockito.times(1)).deleteById(bookId);
    }


}
