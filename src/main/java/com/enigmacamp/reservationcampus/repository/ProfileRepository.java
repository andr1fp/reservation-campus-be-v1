package com.enigmacamp.reservationcampus.repository;

import com.enigmacamp.reservationcampus.model.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProfileRepository extends JpaRepository<Profile, String>, JpaSpecificationExecutor<Profile> {

}
