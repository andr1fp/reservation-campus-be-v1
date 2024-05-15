package com.enigmacamp.reservationcampus.repository;

import com.enigmacamp.reservationcampus.model.facilities.Vehicles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicles, String> {
    List<Vehicles> findbyVehiclesNameContainsIgnoreCase(String name);
}
