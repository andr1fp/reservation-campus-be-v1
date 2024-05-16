package com.enigmacamp.reservationcampus.services;

import com.enigmacamp.reservationcampus.model.entity.Transaction;
import com.enigmacamp.reservationcampus.model.request.TransactionRequest;
import com.enigmacamp.reservationcampus.model.response.TransactionDTO;

import java.util.List;

public interface TransactionService {

    Transaction saveTransaction(TransactionRequest transaction);

    Transaction getTransactionById(String id);

    List<Transaction> getAllTransactions();

    List<Transaction> findTransactionsbyName(String name);

    Transaction updateTransaction(Transaction transaction);

    void deleteTransaction(String id);
}
