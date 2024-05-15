package com.enigmacamp.reservationcampus.model.facilities;

import com.enigmacamp.reservationcampus.model.entity.constant.Availability;
import com.enigmacamp.reservationcampus.model.entity.constant.TypeFacilities;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "mst_tools")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
//@ConfigurationProperties(prefix = "file")
public class Tools{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_tool")
    private String id;

    @Lob
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

    @ManyToOne
    @JoinColumn(name = "id_typefac")
    private TypeFacilities facilities;

    @ManyToOne
    @JoinColumn(name = "id_avail")
    private Availability availability;

}
//    @PrePersist
//    public void setDefaultValue(){
//        if(this.stock > 0){
//            this.avaibility = true;
//        }else {
//            this.avaibility = false;
//        }
//        TypeFacilities defaultFacility = new TypeFacilities();
//        defaultFacility.setId("3");
//        this.setFacility(defaultFacility);

// =======
//     @ManyToOne
//     @JoinColumn(name = "id_avail")
//     private Availability availability;
// >>>>>>> src/main/java/com/enigmacamp/reservationcampus/model/facilities/Tools.java

