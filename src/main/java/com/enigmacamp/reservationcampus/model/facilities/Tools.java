package com.enigmacamp.reservationcampus.model.facilities;

import com.enigmacamp.reservationcampus.model.entity.constant.Availability;
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

    @Column(name = "picture")
    private String picture;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Integer price;

    @Column(name = "stock")
    private Integer stock;

    @OneToOne
    @JoinColumn(name = "id_avail")
    private Availability availability;
}
