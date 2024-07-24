package com.mmk.library.controller;

import com.mmk.library.entity.TransactionDetail;
import com.mmk.library.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

public class TranscationController {

    @Autowired
    private TransactionService transactionService;

    public ResponseEntity<TransactionDetail> newTranscationCreated(@RequestParam int userId, @RequestParam long bookId){

        TransactionDetail newTransactionDetail = transactionService.newTransaction(userId,bookId);

        return new ResponseEntity<TransactionDetail>(newTransactionDetail , HttpStatus.CREATED);

    }
}
