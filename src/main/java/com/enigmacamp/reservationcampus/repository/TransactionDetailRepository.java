package com.enigmacamp.reservationcampus.repository;

import com.enigmacamp.reservationcampus.model.entity.TransactionDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionDetailRepository extends JpaRepository<TransactionDetail, String> {
    List<TransactionDetail> findByTransactionId(String transactionId);
}
