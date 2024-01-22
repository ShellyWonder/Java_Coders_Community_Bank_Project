package com.codercampus.Assignment11.repository;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.codercampus.Assignment11.domain.Transaction;

@Repository
public class TransactionRepository {
	private List<Transaction> transactions = new ArrayList<>(100);

	public TransactionRepository() {
		super();
		populateData();
	}

	// #region Read
	public List<Transaction> findAll() {
		transactions.sort(Comparator.comparing(Transaction::getDate));
		return transactions;
	}
	// #endregion

	// #region Transaction Details by ID
	public Transaction findById(Long transactionId) {
		return transactions.get(transactionId.intValue()); // Cast transactionId to int
	}
	// #endregion

	// #region Create
	public Transaction save(Transaction transaction) {
		transactions.add(transaction);
		return transaction;
	}
	// #endregion

	// #region Delete
	public void delete(Long transactionId) {
		transactions.remove(transactionId.intValue()); // Cast transactionId to int
		throw new UnsupportedOperationException("Unimplemented method 'delete'");
	}
	// #endregion

	// #region populateData
	/* The following method is used to populate the data from the file */
	@SuppressWarnings("unchecked")
	public void populateData() {
		try (FileInputStream fileInputStream = new FileInputStream(
				"src/main/resources/doNotTouch/transactions.doNotTouch");
				ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);) {
			this.transactions = (List<Transaction>) objectInputStream.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

	}
	// #endregion
}
