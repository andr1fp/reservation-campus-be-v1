package com.enigmacamp.reservationcampus.services;

import com.enigmacamp.reservationcampus.model.entity.Transaction;

import java.util.List;

public interface TransactionService {

    Transaction saveTransaction(Transaction transaction);

    Transaction getTransactionById(String id);

    List<Transaction> getAllTransactions();

    List<Transaction> findTransactionsbyName(String name);

    Transaction updateTransaction(Transaction transaction);

    void deleteTransaction(String id);
}
