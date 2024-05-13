package com.enigmacamp.reservationcampus.model.entity;

import com.enigmacamp.reservationcampus.model.entity.constant.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "mst_user")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_user")
    private String id;

    @Column(name = "fullname", nullable = false)
    private String FullName;

    @Column(name = "email",  nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "dateofbirth")
    private Date DateofBirth;

    @Column(name = "photo")
    private String Photo;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "trx_user_role",
            joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_role")
    )
    List<Role> roles;

}
