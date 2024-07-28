package com.mmk.library.service;

import com.mmk.library.entity.BookRequest;
import com.mmk.library.exception.BookRequestNotFoundException;
import com.mmk.library.repository.BookRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookRequestServiceImpl implements BookRequestService {

    @Autowired
    private BookRequestRepository bookRequestRepository;

    @Override
    public BookRequest createBookRequest(BookRequest bookRequest) {
        return bookRequestRepository.save(bookRequest);

    }

    @Override
    public BookRequest updatedBookRequest(BookRequest bookRequest) {
        BookRequest updatedBook = bookRequestRepository.findById(bookRequest.getRequestId()).orElseThrow(() -> new BookRequestNotFoundException("The Request ID " + bookRequest.getRequestId() + "is not available"));
        updatedBook.setBookName(bookRequest.getBookName());
        updatedBook.setAuthorName(bookRequest.getAuthorName());
        updatedBook.setBookEdition(bookRequest.getBookEdition());
        bookRequestRepository.save(updatedBook);

        return updatedBook;
    }
}
