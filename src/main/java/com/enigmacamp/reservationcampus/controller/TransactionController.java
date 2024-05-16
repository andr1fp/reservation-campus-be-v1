package com.enigmacamp.reservationcampus.controller;

import com.enigmacamp.reservationcampus.model.entity.Transaction;
import com.enigmacamp.reservationcampus.model.response.CommonResponse;
import com.enigmacamp.reservationcampus.model.response.TransactionDTO;
import com.enigmacamp.reservationcampus.services.TransactionDetailService;
import com.enigmacamp.reservationcampus.services.TransactionService;
import com.enigmacamp.reservationcampus.utils.constant.APIPath;
import com.enigmacamp.reservationcampus.utils.constant.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(APIPath.API + APIPath.SUBMISSION)
public class TransactionController {

    TransactionDetailService transactionDetailService;
    TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionDetailService transactionDetailService, TransactionService transactionService) {
        this.transactionDetailService = transactionDetailService;
        this.transactionService = transactionService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> saveTransaction(@RequestBody Transaction transaction) {
        String message = String.format(Message.MESSAGE_INSERT);
        System.out.println(transaction);
        Transaction result = transactionService.saveTransaction(transaction);

        CommonResponse<Transaction> response = CommonResponse.<Transaction>builder()
                .statusCode(HttpStatus.OK.value())
                .message(message)
                .data(result)
                .build();
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }
}
