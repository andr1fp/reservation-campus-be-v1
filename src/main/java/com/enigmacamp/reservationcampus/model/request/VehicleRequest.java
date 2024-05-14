package com.enigmacamp.reservationcampus.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class VehicleRequest {
    private String id;
    private String id_availability;
    private String id_facility;
    private String picture;
    private String name;
    private String noPolice;
    private String description;
    private Integer price;
}
