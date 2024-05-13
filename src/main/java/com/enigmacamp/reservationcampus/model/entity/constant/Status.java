package com.enigmacamp.reservationcampus.model.entity.constant;
import jakarta.persistence.*;
import lombok.*;
import com.enigmacamp.reservationcampus.utils.constant.EStatus;

@Entity
@Table(name = "mst_status")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_status")
    private String id;
    @Enumerated(EnumType.STRING)
    private EStatus status;

}
