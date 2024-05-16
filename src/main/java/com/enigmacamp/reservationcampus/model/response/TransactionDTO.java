package com.enigmacamp.reservationcampus.model.response;

import java.util.Date;
import java.util.List;

public class TransactionDTO {
    private String id;
    private String name;
    private String subject;
    private String docoment;
    private Date dateSubmission;
    private Date dateReservation;
    private Date dateReturn;
    private Integer totalItem;
    private Integer grandTotal;
    List<TransactionDetailDTO> transactionDetailDTOs;
}
