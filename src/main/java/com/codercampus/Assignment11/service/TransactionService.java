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

    // #region Transaction Details by ID
    public Transaction findById(Long Id) {
        return transactionRepository.findById(Id);

    }
    // #endregion

    // #region Read
    public List<Transaction> findAll(int page, int size) {
        return transactionRepository.findAll(page, size);
    }

    // needed for pagination
    public int getTotalPages(int size) {
        int totalCount = transactionRepository.count();

        if (totalCount % size > 0) {
            totalCount++;
        }
        return (int) Math.ceil((double) totalCount / size);
    }
    // #endregion

    // #region Delete
    public void delete(Long transactionId) {
        transactionRepository.delete(transactionId);
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }
    // #endregion
}
