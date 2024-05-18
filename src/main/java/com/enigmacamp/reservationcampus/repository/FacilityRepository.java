package com.enigmacamp.reservationcampus.repository;

import com.enigmacamp.reservationcampus.model.entity.Facility;
import com.enigmacamp.reservationcampus.model.entity.constant.TypeFacilities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface FacilityRepository extends JpaRepository<Facility, String> {

    @Query("SELECT f FROM Facility f WHERE f.id NOT IN (" +
            "SELECT td.facility.id FROM TransactionDetail td " +
            "JOIN td.transaction t " +
            "WHERE t.dateReservation <= :endDate AND t.dateReturn >= :startDate)")
    List<Facility> findAvailableFacilities(Date startDate, Date endDate);

    @Query("SELECT f FROM Facility f WHERE f.id IN (" +
            "SELECT td.facility.id FROM TransactionDetail td " +
            "JOIN td.transaction t " +
            "WHERE t.dateReservation <= :endDate AND t.dateReturn >= :startDate)")
    List<Facility> findUnavailableFacilities(Date startDate, Date endDate);

    @Query("SELECT f FROM Facility f WHERE f.name LIKE %:name% AND f.id NOT IN (" +
            "SELECT td.facility.id FROM TransactionDetail td " +
            "JOIN td.transaction t " +
            "WHERE t.dateReservation <= :endDate AND t.dateReturn >= :startDate)")
    List<Facility> findAvailableFacilitiesByName(String name, Date startDate, Date endDate);

    @Query("SELECT f FROM Facility f WHERE f.name LIKE %:name% AND f.id IN (" +
            "SELECT td.facility.id FROM TransactionDetail td " +
            "JOIN td.transaction t " +
            "WHERE t.dateReservation <= :endDate AND t.dateReturn >= :startDate)")
    List<Facility> findUnavailableFacilitiesByName(String name, Date startDate, Date endDate);

    @Query("SELECT f FROM Facility f WHERE f.typeFacilities.id = :typeId AND f.id NOT IN (" +
            "SELECT td.facility.id FROM TransactionDetail td " +
            "JOIN td.transaction t " +
            "WHERE t.dateReservation <= :endDate AND t.dateReturn >= :startDate)")
    List<Facility> findAvailableFacilitiesByType(String typeId, Date startDate, Date endDate);

    @Query("SELECT f FROM Facility f WHERE f.typeFacilities.id = :typeId AND f.id IN (" +
            "SELECT td.facility.id FROM TransactionDetail td " +
            "JOIN td.transaction t " +
            "WHERE t.dateReservation <= :endDate AND t.dateReturn >= :startDate)")
    List<Facility> findUnavailableFacilitiesByType(String typeId, Date startDate, Date endDate);


//    List<Facility> findByNameContainingIgnoreCase(String name);
//
//    List<Facility> findByTypeFacilitiesId(String typeId);




}
