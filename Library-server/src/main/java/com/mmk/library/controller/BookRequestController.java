package com.mmk.library.controller;

import com.mmk.library.entity.Book;
import com.mmk.library.entity.BookRequest;
import com.mmk.library.event.BookRequestEvent;
import com.mmk.library.service.BookRequestService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/Request")
public class BookRequestController {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private BookRequestService bookRequestService;

    public ResponseEntity<BookRequest> createdBookRequest(@RequestBody BookRequest bookRequest , final HttpServletRequest request){

        BookRequest createdBookRequest = bookRequestService.createBookRequest(bookRequest);
        applicationEventPublisher.publishEvent(new BookRequestEvent(bookRequest,applicationUrl(request)));
        return new ResponseEntity<BookRequest>(createdBookRequest, HttpStatus.CREATED);
    }

    private String applicationUrl(HttpServletRequest request) {

        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }

    public ResponseEntity<BookRequest> updateBookRequest(@RequestBody BookRequest bookRequest){
        BookRequest updatedBookRequest = bookRequestService.updatedBookRequest(bookRequest);
        return new ResponseEntity<BookRequest>(updatedBookRequest,HttpStatus.OK);
    }
}
