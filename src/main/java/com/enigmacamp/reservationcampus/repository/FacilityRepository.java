package com.enigmacamp.reservationcampus.repository;

import com.enigmacamp.reservationcampus.model.entity.Facility;
import com.enigmacamp.reservationcampus.model.entity.constant.TypeFacilities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FacilityRepository extends JpaRepository<Facility, String> {
//    Optional<Facility> findByName(String facility);
//    Optional<Facility> findByTypeFacilities(String facility);
//    Optional<Facility> findByAvailabilityIs(String facility);
}
