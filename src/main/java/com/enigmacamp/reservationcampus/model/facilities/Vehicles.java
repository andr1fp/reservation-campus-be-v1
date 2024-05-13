package com.enigmacamp.reservationcampus.model.facilities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "mst_vehicles")
@Getter
@Setter
public class Vehicles extends Facilities {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_vehicle")
    private String id;

    @Column(name = "no_police")
    private String noPolice;
}

