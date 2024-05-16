package com.enigmacamp.reservationcampus.services.impl;

import com.enigmacamp.reservationcampus.model.entity.Facility;
import com.enigmacamp.reservationcampus.model.entity.Profile;
import com.enigmacamp.reservationcampus.model.entity.Transaction;
import com.enigmacamp.reservationcampus.model.entity.TransactionDetail;
import com.enigmacamp.reservationcampus.model.entity.constant.Penalties;
import com.enigmacamp.reservationcampus.model.entity.constant.StatusReservation;
import com.enigmacamp.reservationcampus.model.request.TransactionRequest;
import com.enigmacamp.reservationcampus.model.response.TransactionDetailDTO;
import com.enigmacamp.reservationcampus.repository.PenaltiesRepository;
import com.enigmacamp.reservationcampus.repository.constant.StatusRepository;
import com.enigmacamp.reservationcampus.repository.TransactionRepository;
import com.enigmacamp.reservationcampus.services.*;
import com.enigmacamp.reservationcampus.utils.constant.EPenalties;
import com.enigmacamp.reservationcampus.utils.constant.EStatusReservation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionDetailService transactionDetailService;
    private final ProfileService profileService;
    private final FacilityService facilityService;
    private final StatusRepository statusRepository;
    private final PenaltiesRepository penaltiesRepository;



    @Override
    @Transactional
    public Transaction saveTransaction(TransactionRequest transaction) {
        String profileId = transaction.getId_profile();
        Profile profile = profileService.getProfileById(profileId);
        StatusReservation status = statusRepository.findByStatus(EStatusReservation.STATUS_PROCESSED);
        Penalties penalties = penaltiesRepository.findByName(EPenalties.NOT_PENALTY);


        System.out.println(transaction);
        System.out.println(status);
        System.out.println(penalties);

        Transaction transaction1 = new Transaction();
        transaction1.setSubject(transaction.getSubject());
        transaction1.setDocument(transaction.getDocument());
        transaction1.setProfile(profile);
        transaction1.setDateReservation(Date.valueOf(LocalDate.now()));
        transaction1.setDateSubmission(transaction.getDateReservation());
        transaction1.setDateReturn(transaction.getDateReturn());
        transaction1.setStatus(status);
        transaction1.setPenalties(penalties);


        Transaction transactionResult = transactionRepository.save(transaction1);
        List<TransactionDetail> transactionDetailList = new ArrayList<>();


        for(TransactionDetailDTO transactionDetail : transaction.getTransactionDetail()) {
            TransactionDetail transactionDetail1 = new TransactionDetail();
            transactionDetail1.setTransaction(transaction1);
            transactionDetail1.setPrice(transactionDetail.getPrice());
            transactionDetail1.setQuantity(transactionDetail.getQuantity());
            transactionDetail1.setFacility(facilityService.getFacilityById(transactionDetail.getId()));

            Facility facility = facilityService.getFacilityById(transactionDetail.getId());
            Integer stok = facility.getQuantity();
            Integer quantity = transactionDetail.getQuantity();
            if(stok == 0 || stok < quantity){
                throw new RuntimeException("STOK TIDAK CUKUP");
            }else{
                facility.setQuantity(stok - quantity);
                facilityService.updateFacility(facility);
            }

            transactionDetailService.saveTransactionDetail(transactionDetail1);
            transactionDetailList.add(this.transactionDetailService.saveTransactionDetail(transactionDetail1));

        }

        transactionResult.setTransactionDetail(transactionDetailList);
        return transactionResult;

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
