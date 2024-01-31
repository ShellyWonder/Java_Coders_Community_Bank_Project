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
	public List<Transaction> findAll(int page, int size) {
		transactions.sort(Comparator.comparing(Transaction::getDate));
		int startIndex = page * size;
		int endIndex = Math.min((startIndex + size), transactions.size());
		return transactions.subList(startIndex, endIndex);
	}

	// Needed for pagination
	public int count() {
		return transactions.size();
	}
	// #endregion

	// #region Transaction Details by ID
	public Transaction findById(Long Id) {
		return transactions.get(Id.intValue()); // Cast Id to int
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
				ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
			List<Transaction> loadedTransactions = (List<Transaction>) objectInputStream.readObject();
			transactions.clear();
			transactions.addAll(loadedTransactions);
		} catch (IOException e) {
			// Handle IO exception (e.g., log error, provide a default data, or throw a
			// custom exception)
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// Handle class not found exception (e.g., log error or throw a custom
			// exception)
			e.printStackTrace();
		}
	}

	// #endregion
}
