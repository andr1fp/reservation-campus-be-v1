package com.enigmacamp.reservationcampus.services;

import com.enigmacamp.reservationcampus.model.entity.Transaction;
import com.enigmacamp.reservationcampus.model.request.TransactionRequest;
import com.enigmacamp.reservationcampus.model.request.UpdateStatusRequest;
import com.enigmacamp.reservationcampus.model.response.TransactionDTO;

import java.util.List;

public interface TransactionService {

    Transaction saveTransaction(TransactionRequest transaction);

    TransactionDTO getTransactionById(String id);

    List<TransactionDTO> getAllTransaction();

    List<TransactionDTO> findTransactionsbyName(String name);

    Transaction updateTransaction(Transaction transaction);

    void deleteTransaction(String id);

    void cancelTransaction(String id);

    void updateTransactionStatus(String id, UpdateStatusRequest request);
}
