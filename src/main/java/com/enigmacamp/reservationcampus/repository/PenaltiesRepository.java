package com.enigmacamp.reservationcampus.repository;

import com.enigmacamp.reservationcampus.model.entity.constant.Penalties;
import com.enigmacamp.reservationcampus.utils.constant.EPenalties;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PenaltiesRepository extends JpaRepository<Penalties, String> {
    <Optional> Penalties findByName(EPenalties penalties);

}
