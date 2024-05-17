package com.enigmacamp.reservationcampus.model.response;

import com.enigmacamp.reservationcampus.model.entity.TransactionDetail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionDetailDTO {
    private String id;
    private String name;
    private Integer quantity;
    private Integer price;

    public static TransactionDetailDTO fromEntity(TransactionDetail transactionDetail) {
        return TransactionDetailDTO.builder()
                .id(transactionDetail.getId())
                .name(transactionDetail.getFacility().getName())
                .quantity(transactionDetail.getQuantity())
                .price(transactionDetail.getPrice())
                .build();
    }
}
