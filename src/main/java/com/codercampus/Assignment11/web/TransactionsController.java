package com.codercampus.Assignment11.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.codercampus.Assignment11.domain.Transaction;
import com.codercampus.Assignment11.service.TransactionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TransactionsController {

    @Autowired
    private TransactionService transactionService;

    // #region Read
    @GetMapping("/transactions")
    public String getAllTransactions(ModelMap model, @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        List<Transaction> transactions = transactionService.findAll(page, size);

        int totalPages = transactionService.getTotalPages(size);
        Transaction transaction = new Transaction();
        model.put("transactions", transactions);
        model.put("transaction", transaction);
        // Enables Thymeleaf template to know which navbar link to highlight
        model.addAttribute("activePage", "transactions");
        // Enables Thymeleaf template pagination
        model.addAttribute("transactions", transactions);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        return "/transactions";
    }

    // #endregion

    // #region Transaction Details by ID
    @GetMapping("/transaction_details/{id}")
    public String getTransactionDetails(@PathVariable Long id, ModelMap model) {
        Transaction transaction = transactionService.findById(id);
        model.addAttribute("transaction", transaction);
        return "transaction_details";
    }

    // #endregion

    // #region Update
    @PostMapping("/update_transaction/{transactionId}")
    public String postTransactions(Transaction transaction) {
        Transaction savedTransaction = transactionService.save(transaction);
        return "redirect:/transactions/" + savedTransaction.getId();
    }
    // #endregion

    // #region Create
    @GetMapping("/add_transaction")
    public String showAddTransactionForm(Model model) {

        Transaction transaction = new Transaction();
        model.addAttribute("transaction", transaction);

        return "add_transaction";
    }

    @PostMapping("/add_transaction")
    public String addTransaction(Transaction transaction) {
        transactionService.save(transaction);
        return "redirect:/transactions";
    }

    // #endregion

    // #region Delete
    @PostMapping("/delete_transaction/{transactionId}")
    public String deleteTransaction(@PathVariable Long transactionId) {
        transactionService.delete(transactionId);
        return "redirect:/transactions";
    }
    // #endregion

    // #region Static views
    @GetMapping("/")
    public String index(ModelMap model) {
        model.addAttribute("activePage", "index");
        return "index"; // The name of the Thymeleaf template for the index view
    }

    @GetMapping("/controller_code")
    public String controllerCode() {
        return "controller_code"; // The name of the Thymeleaf template for the controller code view
    }

    @GetMapping("/service_code")
    public String serviceCode() {
        return "service_code"; // The name of the Thymeleaf template for the service code view
    }

    @GetMapping("/repository_code")
    public String repositoryCode() {
        return "repository_code"; // The name of the Thymeleaf template for the repository code view
    }
    // #endregion
}
