package com.enigmacamp.reservationcampus.model.facilities;


import com.enigmacamp.reservationcampus.model.entity.constant.Availability;
import com.enigmacamp.reservationcampus.model.entity.constant.TypeFacilities;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Table(name = "mst_facilities")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Facilities {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_fac")
    private String id;

    @OneToOne
    @JoinColumn(name = "id_typefac")
    private TypeFacilities facility;

}
