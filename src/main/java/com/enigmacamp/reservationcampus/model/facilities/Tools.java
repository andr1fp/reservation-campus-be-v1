package com.enigmacamp.reservationcampus.model.facilities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "mst_tools")
@Getter
@Setter
public class Tools extends Facilities {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_tool")
    private String id;

    @Column(name = "stock")
    private Integer stock;
}
