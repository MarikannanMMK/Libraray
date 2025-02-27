package com.mmk.library.service;

import com.mmk.library.entity.Book;
import com.mmk.library.entity.TransactionDetail;
import com.mmk.library.entity.User;
import com.mmk.library.exception.BookNotFoundException;
import com.mmk.library.exception.UserNotFoundException;
import com.mmk.library.model.EmailDetails;
import com.mmk.library.repository.BookRepository;
import com.mmk.library.repository.TransactionRepository;
import com.mmk.library.repository.UserRepository;
import com.mmk.library.utility.Utill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;


@Service
public class TransactionlServiceImpl implements TransactionService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private Utill utill;

    @Override
    public TransactionDetail newTransaction(int userId, long bookId) {

        LocalDateTime localDateTime = null;
        int availabeStock = 0;

        TransactionDetail transactionDetail = new TransactionDetail();
        Book requestedBook = bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException("Book With Given ID :" + bookId + " Not Available"));
        User borrowedUser = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User With Given ID :" + userId + " Not Available"));
        if (requestedBook != null && requestedBook.getAvailableStock() > 0 && borrowedUser != null) {
            transactionDetail.setIssueDate(localDateTime = localDateTime.now());
            transactionDetail.setDueDate(localDateTime.plusDays(30));
            transactionDetail.setBook(requestedBook);
            transactionDetail.setUser(borrowedUser);
            availabeStock = requestedBook.getAvailableStock();
            requestedBook.setAvailableStock(availabeStock - 1);
        }
        bookRepository.save(requestedBook);
        transactionRepository.save(transactionDetail);
        EmailDetails emailDetails = new EmailDetails();
        emailDetails.setRecipient(borrowedUser.getEmail());
        emailDetails.setSubject("New book is borrowed from the Library" );
        emailDetails.setMsgBody("Hello Student new book with the name "+ requestedBook.getTitle()+"is from library on "+String.valueOf(localDateTime.now()));
        utill.sendMail(emailDetails);
        return transactionDetail;
    }
}
