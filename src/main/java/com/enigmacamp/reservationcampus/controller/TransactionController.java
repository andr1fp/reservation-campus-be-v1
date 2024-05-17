package com.enigmacamp.reservationcampus.controller;

import com.enigmacamp.reservationcampus.model.entity.Transaction;
import com.enigmacamp.reservationcampus.model.request.TransactionRequest;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


    @GetMapping( APIPath.TRANSACTION + "/detail/{id}")
    public ResponseEntity<?> getTransaction(@PathVariable("id") String id) {
        TransactionDTO result = transactionService.getTransactionById(id);
        CommonResponse<TransactionDTO> response = CommonResponse.<TransactionDTO>builder()
                .statusCode(HttpStatus.OK.value())
                .message(Message.MESSAGE_READ)
                .data(result)
                .build();
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }


    @GetMapping( APIPath.TRANSACTION + "/{name}")
    public ResponseEntity<?> getTransactionByName(@PathVariable("name") String name) {
        List<TransactionDTO> result = transactionService.findTransactionsbyName(name);
        CommonResponse<List<TransactionDTO>> response = CommonResponse.<List<TransactionDTO>>builder()
                .statusCode(HttpStatus.OK.value())
                .message(Message.MESSAGE_READ)
                .data(result)
                .build();
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }



    @GetMapping(APIPath.TRANSACTION)
    public ResponseEntity<?> getAllTransaction() {
        List<TransactionDTO> result = transactionService.getAllTransaction();
        CommonResponse<List<TransactionDTO>> response = CommonResponse.<List<TransactionDTO>>builder()
                .statusCode(HttpStatus.OK.value())
                .message(Message.MESSAGE_READ)
                .data(result)
                .build();
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @PostMapping(APIPath.TRANSACTION)
    public ResponseEntity<?> saveTransaction(@RequestBody TransactionRequest transactionRequest) {
        String message = String.format(Message.MESSAGE_SAVE_SUBMISSION);
        System.out.println(transactionRequest);
        Transaction result = transactionService.saveTransaction(transactionRequest);

        CommonResponse<Transaction> response = CommonResponse.<Transaction>builder()
                .statusCode(HttpStatus.OK.value())
                .message(message)
                .data(result)
                .build();
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @PutMapping("/transaction")
    public ResponseEntity<?> updateTransaction(@RequestBody Transaction transaction) {
        String message = String.format(Message.MESSAGE_UPDATE);
        Transaction result = transactionService.updateTransaction(transaction);
        CommonResponse<Transaction> response = CommonResponse.<Transaction>builder()
                .statusCode(HttpStatus.OK.value())
                .message(message)
                .data(result)
                .build();
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);

    }

    @DeleteMapping("/transaction/{id}")
    public ResponseEntity<?> deleteTransaction(@PathVariable("id") String id) {
        String message = String.format(Message.MESSAGE_DELETE);
        transactionService.deleteTransaction(id);
        CommonResponse<Transaction> response = CommonResponse.<Transaction>builder()
                .statusCode(HttpStatus.OK.value())
                .message(message)
                .build();
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @PutMapping("/transaction/{id}")
    public ResponseEntity<?> cancelTransaction(@PathVariable("id") String id) {
        String message = String.format(Message.MESSAGE_CANCELED);
        transactionService.cancelTransaction(id);
        CommonResponse<Transaction> response = CommonResponse.<Transaction>builder()
                .statusCode(HttpStatus.OK.value())
                .message(message)
                .build();
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }
}
