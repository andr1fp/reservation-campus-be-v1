package com.enigmacamp.reservationcampus.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FacilityAvailabilityResponse {
    private String id;
    private String name;
    private String information;
    private String picture;
    private Integer quantity;
    private Integer price;
    private String typeFacilities;
    private String availability;

}
