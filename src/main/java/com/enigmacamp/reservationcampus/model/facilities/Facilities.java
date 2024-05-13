package com.enigmacamp.reservationcampus.model.facilities;


import com.enigmacamp.reservationcampus.model.entity.constant.Availability;
import com.enigmacamp.reservationcampus.model.entity.constant.TypeFacilities;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "mst_facilities")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@MappedSuperclass
public class Facilities {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_fac")
    private String id;

    @Column(name = "picture")
    private String picture;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Integer price;

    //Fasilities
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "trx_fac_typefac",
            joinColumns = @JoinColumn(name = "id_fac"),
            inverseJoinColumns = @JoinColumn(name = "id_typefac")
    )
    List<TypeFacilities> facilities;

    //Availability
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "trx_fac_avail",
            joinColumns = @JoinColumn(name = "id_fac"),
            inverseJoinColumns = @JoinColumn(name = "id_avail")
    )
    List<Availability> availability;

}
