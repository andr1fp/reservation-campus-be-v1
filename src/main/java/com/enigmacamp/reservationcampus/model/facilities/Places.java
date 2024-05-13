package com.enigmacamp.reservationcampus.model.facilities;


import com.enigmacamp.reservationcampus.model.entity.constant.Availability;
import com.enigmacamp.reservationcampus.model.entity.constant.TypeFacilities;
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

    @Column(name = "picture")
    private String picture;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Integer price;

    @Column(name = "capacity")
    private Integer capacity;

    @OneToOne
    @JoinColumn(name = "id_avail")
    private Availability availability;


}
