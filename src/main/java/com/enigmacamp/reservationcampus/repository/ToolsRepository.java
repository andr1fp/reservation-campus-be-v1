package com.enigmacamp.reservationcampus.repository;

import com.enigmacamp.reservationcampus.model.facilities.Tools;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToolsRepository extends JpaRepository<Tools, String> {
    List<Tools> findByNameContainsIgnoreCase(String name);
}
