package com.enigmacamp.reservationcampus.model.facilities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "mst_tools")
@Getter
@Setter
@ConfigurationProperties(prefix = "file")
public class Tools extends Facilities {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_tool")
    private String id;

    @Lob
    @Column(name = "picture")
    private byte[] picture;

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
// =======
//     @ManyToOne
//     @JoinColumn(name = "id_avail")
//     private Availability availability;
// >>>>>>> src/main/java/com/enigmacamp/reservationcampus/model/facilities/Tools.java
}
