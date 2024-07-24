package com.mmk.library.service;


import com.mmk.library.entity.TransactionDetail;

public interface TransactionService {

    TransactionDetail newTransaction(int userId,long bookId);
}
