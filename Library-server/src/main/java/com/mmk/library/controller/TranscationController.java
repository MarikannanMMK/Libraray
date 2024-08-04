package com.mmk.library.controller;

import com.mmk.library.entity.TransactionDetail;
import com.mmk.library.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/Transaction")
public class TranscationController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping(value = "/newBorrow")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER') ")
    @Operation(summary = "This Method is for creating the new Borrow Transaction Object by providing User ID and Book Id as Input Parameter")
    public ResponseEntity<TransactionDetail> newTransactionCreated(@RequestParam int userId, @RequestParam long bookId){

        TransactionDetail newTransactionDetail = transactionService.newTransaction(userId,bookId);

        return new ResponseEntity<TransactionDetail>(newTransactionDetail , HttpStatus.CREATED);

    }
}
