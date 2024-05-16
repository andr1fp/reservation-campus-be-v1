package com.enigmacamp.reservationcampus.repository;

import com.enigmacamp.reservationcampus.model.entity.constant.StatusReservation;
import com.enigmacamp.reservationcampus.model.entity.constant.StatusReservation;
import com.enigmacamp.reservationcampus.utils.constant.EStatusReservation;
import com.enigmacamp.reservationcampus.utils.constant.EStatusReservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<StatusReservation, String> {
    <Optional> StatusReservation findByStatus(EStatusReservation status);
}
