package com.enigmacamp.reservationcampus.model.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "mst_profile")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class Profile {

    @Id
    @Column(name = "id_profile")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

//    @Column(nullable = false)
    private Integer NIM;

//    @Column(nullable = false)
    private String fullName;

    @Column(name = "dateofbirth")
    private Date dateofbirth;

    @Column(name = "photo")
    private String photo;

    private String phone;

    @OneToOne
    @JoinColumn(name = "id_user")
    private User user;

}
