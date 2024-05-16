package com.enigmacamp.reservationcampus.model.response;

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

}
