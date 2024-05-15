package com.enigmacamp.reservationcampus.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlaceRequest {
    private String id;
    private String id_availability;
    private String id_facility;
    private String name;
    private String picture;
    private String description;
    private Integer price;
    private Integer capacity;
}
