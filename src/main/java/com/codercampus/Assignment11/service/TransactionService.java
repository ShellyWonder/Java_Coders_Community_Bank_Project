package com.codercampus.Assignment11.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codercampus.Assignment11.repository.TransactionRepository;
import com.codercampus.Assignment11.domain.*;

@Service
public class TransactionService {

    private Long transactionId = 101L;

    @Autowired
    private TransactionRepository transactionRepository;

    // #region Create
    public Transaction save(Transaction transaction) {
        if (transaction.getId() == null) {

            transaction.setId(transactionId++);
        }
        return transactionRepository.save(transaction);

    }

    // #endregion
    public Transaction findById(Long transactionId) {
        return transactionRepository.findById(transactionId);

    }

    // #region Read
    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }
    // #endregion
}
