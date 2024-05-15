package com.enigmacamp.reservationcampus.model.facilities;

import com.enigmacamp.reservationcampus.model.entity.constant.Availability;
import com.enigmacamp.reservationcampus.model.entity.constant.TypeFacilities;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "mst_vehicles")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class Vehicles{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_vehicle")
    private String id;

    @Column(name = "picture")
    private String picture;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Integer price;

    @Column(name = "no_police")
    private String noPolice;

    @ManyToOne
    @JoinColumn(name = "id_typefac")
    private TypeFacilities facilities;

    @ManyToOne
    @JoinColumn(name = "id_avail")
    private Availability availability;
}

