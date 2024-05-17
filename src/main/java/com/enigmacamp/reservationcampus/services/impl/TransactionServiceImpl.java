package com.enigmacamp.reservationcampus.services.impl;

import com.enigmacamp.reservationcampus.model.entity.Facility;
import com.enigmacamp.reservationcampus.model.entity.Profile;
import com.enigmacamp.reservationcampus.model.entity.Transaction;
import com.enigmacamp.reservationcampus.model.entity.TransactionDetail;
import com.enigmacamp.reservationcampus.model.entity.constant.Availability;
import com.enigmacamp.reservationcampus.model.entity.constant.Penalties;
import com.enigmacamp.reservationcampus.model.entity.constant.StatusReservation;
import com.enigmacamp.reservationcampus.model.request.TransactionRequest;
import com.enigmacamp.reservationcampus.model.response.TransactionDTO;
import com.enigmacamp.reservationcampus.model.response.TransactionDetailDTO;
import com.enigmacamp.reservationcampus.repository.PenaltiesRepository;
import com.enigmacamp.reservationcampus.repository.constant.AvailabilityRepository;
import com.enigmacamp.reservationcampus.repository.constant.StatusRepository;
import com.enigmacamp.reservationcampus.repository.TransactionRepository;
import com.enigmacamp.reservationcampus.services.*;
import com.enigmacamp.reservationcampus.utils.constant.EAvailability;
import com.enigmacamp.reservationcampus.utils.constant.EPenalties;
import com.enigmacamp.reservationcampus.utils.constant.EStatusReservation;
import com.enigmacamp.reservationcampus.utils.exception.DataNotFoundException;
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
    private final AvailabilityRepository availabilityRepository;

    @Override
    @Transactional
    public Transaction saveTransaction(TransactionRequest transaction) {
        String profileId = transaction.getId_profile();
        Profile profile = profileService.getProfileById(profileId);
        StatusReservation status = statusRepository.findByStatus(EStatusReservation.STATUS_PROCESSED);
        Penalties penalties = penaltiesRepository.findByName(EPenalties.NOT_PENALTY);

        Availability notAvailability = availabilityRepository.findByName(EAvailability.AVAILABILITY_NO);

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
                facility.setAvailability(notAvailability);
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
    public TransactionDTO getTransactionById(String id) {
        if(transactionRepository.findById(id).isPresent()){
            Transaction transaction = transactionRepository.findById(id).get();
            TransactionDTO transactionDTO = new TransactionDTO();
            Profile profile = profileService.getProfileById(transaction.getProfile().getId());

            transactionDTO.setId(transaction.getId());
            transactionDTO.setName(profile.getFullName());
            transactionDTO.setDocument(transaction.getDocument());
            transactionDTO.setSubject(transaction.getSubject());
            transactionDTO.setDateReservation(transaction.getDateReservation());
            transactionDTO.setDateSubmission(transaction.getDateSubmission());
            transactionDTO.setDateReturn(transaction.getDateReturn());

            List<TransactionDetailDTO> transactionDetailDtoList = new ArrayList<>();

            int totalItem = 0;
            int grandTotal = 0;

            for(TransactionDetail transactionDetail : transaction.getTransactionDetail()){
                TransactionDetailDTO transactionDetailDTO = new TransactionDetailDTO();
                transactionDetailDTO.setId(transactionDetail.getId());
                transactionDetailDTO.setPrice(transactionDetail.getPrice());
                transactionDetailDTO.setQuantity(transactionDetail.getQuantity());
                Facility facility = facilityService.getFacilityById(transactionDetail.getFacility().getId());
                transactionDetailDTO.setName(facility.getName());

                totalItem += transactionDetail.getQuantity();
                grandTotal += transactionDetail.getQuantity() * transactionDetail.getPrice();

                transactionDetailDtoList.add(transactionDetailDTO);

            }
            transactionDTO.setTransactionDetailDTO(transactionDetailDtoList);
            transactionDTO.setTotalItem(totalItem);
            transactionDTO.setGrandTotal(grandTotal);

            return transactionDTO;
        }else{
            throw new DataNotFoundException("Data not Found");
        }

    }

    @Override
    public List<TransactionDTO> getAllTransaction() {
        List<Transaction> transactions = transactionRepository.findAll();
        TransactionDTO transactionDTO = new TransactionDTO();

        List<TransactionDTO> transactionDTOList = new ArrayList<>();

        for(Transaction transaction : transactions){
            Profile profile = profileService.getProfileById(transaction.getProfile().getId());
            transactionDTO.setId(transaction.getId());
            transactionDTO.setName(profile.getFullName());
            transactionDTO.setDocument(transaction.getDocument());
            transactionDTO.setSubject(transaction.getSubject());
            transactionDTO.setDateReservation(transaction.getDateReservation());
            transactionDTO.setDateSubmission(transaction.getDateSubmission());
            transactionDTO.setDateReturn(transaction.getDateReturn());

            transactionDTOList.add(transactionDTO);
        }

        return  transactionDTOList;

    }



    @Override
    public List<TransactionDTO> findTransactionsbyName(String name) {
        List<Transaction> transactions = transactionRepository.findBySubject(name);
        List<TransactionDTO> transactionDTOList = new ArrayList<>();

        TransactionDTO transactionDTO = new TransactionDTO();

        for(Transaction transaction : transactions){
            Profile profile = profileService.getProfileById(transaction.getProfile().getId());
            transactionDTO.setId(transaction.getId());
            transactionDTO.setName(profile.getFullName());
            transactionDTO.setDocument(transaction.getDocument());
            transactionDTO.setSubject(transaction.getSubject());
            transactionDTO.setDateReservation(transaction.getDateReservation());
            transactionDTO.setDateSubmission(transaction.getDateSubmission());
            transactionDTO.setDateReturn(transaction.getDateReturn());

            transactionDTOList.add(transactionDTO);
        }
        return transactionDTOList;

    }

    @Override
    @Transactional
    public Transaction updateTransaction(Transaction transaction) {
        Transaction transaction1 = transactionRepository.findById(transaction.getId()).get();
        transaction1.setSubject(transaction.getSubject());
        transaction1.setDocument(transaction.getDocument());
        transaction1.setProfile(transaction.getProfile());
        transaction1.setDateReservation(transaction.getDateReservation());
        transaction1.setDateSubmission(transaction.getDateSubmission());
        transaction1.setDateReturn(transaction.getDateReturn());
        transaction1.setStatus(transaction.getStatus());
        transaction1.setPenalties(transaction.getPenalties());

        StatusReservation status = statusRepository.findById(transaction.getStatus().getId()).get();
        if(status.getStatus() == EStatusReservation.STATUS_REJECTED || status.getStatus() == EStatusReservation.STATUS_CANCELED || status.getStatus() == EStatusReservation.STATUS_COMPLETED){
            for (TransactionDetail transactionDetail : transaction.getTransactionDetail()) {
                Facility facility = facilityService.getFacilityById(transactionDetail.getFacility().getId());
                facility.setQuantity(facility.getQuantity() + transactionDetail.getQuantity());
                facilityService.updateFacility(facility);
            }
        }
        return transactionRepository.save(transaction1);
    }

    @Override
    public void deleteTransaction(String id) {
        Transaction transaction = transactionRepository.findById(id).get();
        if(transaction.getId() != null){
            transactionRepository.delete(transaction);
        } else {
            throw new DataNotFoundException("Data Not Found");
        }
    }

    @Override
    public void cancelTransaction(String id) {
        Transaction transaction = transactionRepository.findById(id).get();
        System.out.println(transaction);
        StatusReservation status = statusRepository.findByStatus(EStatusReservation.STATUS_CANCELED);
        if(transaction.getId()!= null){
            transaction.setStatus(status);
            transactionRepository.save(transaction);
        } else{
            throw new DataNotFoundException("Data Not Found");
        }
    }


}
