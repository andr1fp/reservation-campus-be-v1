package com.enigmacamp.reservationcampus.repository;

import com.enigmacamp.reservationcampus.model.entity.Facility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnalyticsRepository extends JpaRepository<Facility, String> {

    @Query("SELECT f.typeFacilities.name, COUNT(f) FROM Facility f GROUP BY f.typeFacilities.name")
    List<Object[]> countFacilitiesByType();

    @Query("SELECT f.availability.name, COUNT(f) FROM Facility f GROUP BY f.availability.name")
    List<Object[]> countFacilitiesByAvailability();

    @Query("SELECT t.status.name, COUNT(t) FROM Transaction t GROUP BY t.status.name")
    List<Object[]> countTransactionsByStatus();
}
