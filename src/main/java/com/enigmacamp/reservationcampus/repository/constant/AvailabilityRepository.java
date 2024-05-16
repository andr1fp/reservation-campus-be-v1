package com.enigmacamp.reservationcampus.repository.constant;

import com.enigmacamp.reservationcampus.model.entity.constant.Availability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvailabilityRepository extends JpaRepository<Availability, String> {
//    Optional<Availability> findByName(EAvailability name);
}
