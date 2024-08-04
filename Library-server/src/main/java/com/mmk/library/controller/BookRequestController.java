package com.mmk.library.controller;

import com.mmk.library.entity.Book;
import com.mmk.library.entity.BookRequest;
import com.mmk.library.event.BookRequestEvent;
import com.mmk.library.service.BookRequestService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/Request")
public class BookRequestController {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private BookRequestService bookRequestService;

    @PostMapping(value = "/newBookRequest")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER') ")
    @Operation(summary = "This Method is for create a new  Book Request object By Providing Book Request as input")
    public ResponseEntity<BookRequest> createdBookRequest(@RequestBody BookRequest bookRequest , final HttpServletRequest request){
        BookRequest createdBookRequest = bookRequestService.createBookRequest(bookRequest);
        applicationEventPublisher.publishEvent(new BookRequestEvent(bookRequest,applicationUrl(request)));
        return new ResponseEntity<BookRequest>(createdBookRequest, HttpStatus.CREATED);
    }

    @Operation(summary = "This Method is for generating application URL for mail purpose")
    private String applicationUrl(HttpServletRequest request) {

        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }

    @PostMapping(value = "/updateRequest")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER') ")
    @Operation(summary = "This Method is for update the existing Book request object By Providing Book request object as input")
    public ResponseEntity<BookRequest> updateBookRequest(@RequestBody BookRequest bookRequest){
        BookRequest updatedBookRequest = bookRequestService.updatedBookRequest(bookRequest);
        return new ResponseEntity<BookRequest>(updatedBookRequest,HttpStatus.OK);
    }

    @GetMapping(value = "/allRequests")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER') ")
    @Operation(summary = "This Method is for fetching all the BookRequests objects")
    public ResponseEntity<List<BookRequest>> getAllBookRequest (){
        List<BookRequest> allBookRequest = bookRequestService.getAllBookRequest();
        return new ResponseEntity<List<BookRequest>>(allBookRequest,HttpStatus.OK);
    }
}
