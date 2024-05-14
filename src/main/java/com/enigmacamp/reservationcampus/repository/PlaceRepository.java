package com.enigmacamp.reservationcampus.repository;

import com.enigmacamp.reservationcampus.model.facilities.Places;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaceRepository extends JpaRepository<Places, String> {
}
