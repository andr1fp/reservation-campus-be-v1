package com.enigmacamp.reservationcampus.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
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
    List<TransactionDetailDTO> transactionDetailDTO;
}
