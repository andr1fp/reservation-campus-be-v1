package com.enigmacamp.reservationcampus.model.facilities;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "mst_places")
@Setter
@Getter
public class Places extends Facilities{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_place")
    private String id;

    @Column(name = "capacity")
    private Integer capacity;


}
