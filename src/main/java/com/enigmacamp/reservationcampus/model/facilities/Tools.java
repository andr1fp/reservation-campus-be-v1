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

    private Boolean avaibility;

    @PrePersist
    public void setDefaultValue(){
        if(this.stock > 0){
            this.avaibility = true;
        }else {
            this.avaibility = false;
        }
//        TypeFacilities defaultFacility = new TypeFacilities();
//        defaultFacility.setId("3");
//        this.setFacility(defaultFacility);
    }
}
