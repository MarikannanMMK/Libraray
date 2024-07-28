package com.mmk.library.service;

import com.mmk.library.entity.BookRequest;

public interface BookRequestService{
    BookRequest createBookRequest(BookRequest bookRequest);

    BookRequest updatedBookRequest(BookRequest bookRequest);
}
