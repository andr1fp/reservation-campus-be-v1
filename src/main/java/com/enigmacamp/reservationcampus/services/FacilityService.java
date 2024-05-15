package com.enigmacamp.reservationcampus.services;

import com.enigmacamp.reservationcampus.model.entity.Facility;
import com.enigmacamp.reservationcampus.model.request.FacilityRequest;
import com.enigmacamp.reservationcampus.model.response.FacilityResponse;

import java.util.List;

public interface FacilityService {

    FacilityRequest saveFacility(FacilityRequest facilityRequest);

    Facility getFacilityById(String id);

    Facility updateFacility(Facility facility);

    List<Facility> getAllFacilities();

    List<Facility> getFacilityByName(String name);

    List<Facility> getFacilitiesByType(String type);

    void deleteFacility(String id);

}
