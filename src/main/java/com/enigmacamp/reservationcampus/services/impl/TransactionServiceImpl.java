package com.enigmacamp.reservationcampus.services.impl;

import com.enigmacamp.reservationcampus.model.entity.Facility;
import com.enigmacamp.reservationcampus.model.entity.Transaction;
import com.enigmacamp.reservationcampus.model.entity.TransactionDetail;
import com.enigmacamp.reservationcampus.model.entity.constant.Penalties;
import com.enigmacamp.reservationcampus.model.entity.constant.StatusReservation;
import com.enigmacamp.reservationcampus.repository.TransactionRepository;
import com.enigmacamp.reservationcampus.services.*;
import com.enigmacamp.reservationcampus.utils.constant.EPenalties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    TransactionRepository transactionRepository;
    TransactionDetailService transactionDetailService;
    ProfileService profileService;
    FacilityService facilityService;

    @Autowired
    public TransactionServiceImpl(
            TransactionRepository transactionRepository,
            TransactionDetailService transactionDetailService,
            FacilityService facilityService,
            ProfileService profileService) {
        this.transactionRepository = transactionRepository;
        this.transactionDetailService = transactionDetailService;
        this.profileService = profileService;
        this.facilityService = facilityService;
    }


    @Override
    @Transactional
    public Transaction saveTransaction(Transaction transaction) {
        transaction.setDateSubmission(Date.valueOf(LocalDate.now()));

        StatusReservation processedStatusReservation = new StatusReservation();
        processedStatusReservation.setStatus(EStatus.STATUS_PROCESSED);
        transaction.setStatus(processedStatusReservation);

        Penalties noPenalty = new Penalties();
        noPenalty.setName(EPenalties.NOT_PENALTY);
        transaction.setPenalties(noPenalty);
        Transaction savedTransaction = transactionRepository.save(transaction);
        System.out.println("SUCCESSFUL");
        System.out.println(savedTransaction);

        for (TransactionDetail transactionDetail : transaction.getTransactionDetail()) {
            Facility facility = facilityService.getFacilityById(transactionDetail.getFacility().getId());
            transactionDetail.setPrice(facility.getPrice());
            transactionDetailService.saveTransactionDetail(transactionDetail);
        }

        System.out.println(savedTransaction);
        return savedTransaction;
    }

    @Override
    public Transaction getTransactionById(String id) {
        return null;
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return List.of();
    }

    @Override
    public List<Transaction> findTransactionsbyName(String name) {
        return List.of();
    }

    @Override
    public Transaction updateTransaction(Transaction transaction) {
        return null;
    }

    @Override
    public void deleteTransaction(String id) {

    }
}
