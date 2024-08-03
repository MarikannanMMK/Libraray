package com.mmk.library.service;

import com.mmk.library.entity.BookRequest;

import java.util.List;

public interface BookRequestService{
    BookRequest createBookRequest(BookRequest bookRequest);

    BookRequest updatedBookRequest(BookRequest bookRequest);

    List<BookRequest> getAllBookRequest();
}
