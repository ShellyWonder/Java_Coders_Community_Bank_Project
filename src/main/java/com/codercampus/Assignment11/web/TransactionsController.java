package com.codercampus.Assignment11.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.codercampus.Assignment11.domain.Transaction;
import com.codercampus.Assignment11.service.TransactionService;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TransactionsController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/transactions")
    public String getAllTransactions(ModelMap model) {
        List<Transaction> transactions = transactionService.findAll();

        Transaction transaction = new Transaction();
        model.put("transactions", transactions);
        model.put("transaction", transaction);
        return "/transactions";
    }

    @GetMapping("/transactions/{transactionId}")
    public String getTransactionById(@PathVariable Long transactionId, ModelMap model) {
        Transaction transaction = transactionService.findById(transactionId);
        model.put("transaction", transaction);
        return "transactions";
    }

    // update
    @PostMapping("/transactions/update/{transactionId}")
    public String PostPeople(Transaction transaction) {
        Transaction savedTransaction = transactionService.save(transaction);
        return "redirect:/transactions/" + savedTransaction.getId();
    }

    @PostMapping("/transactions/add")
    public String addTransaction(Transaction transaction) {
        transactionService.save(transaction);
        return "redirect:/transactions";
    }

}
