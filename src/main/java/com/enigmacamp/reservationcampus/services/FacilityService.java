package com.enigmacamp.reservationcampus.services;

import com.enigmacamp.reservationcampus.model.entity.Facility;
import com.enigmacamp.reservationcampus.model.request.FacilityRequest;
import com.enigmacamp.reservationcampus.model.response.FacilityResponse;

import java.sql.Date;
import java.util.List;

public interface FacilityService {

    FacilityRequest saveFacility(FacilityRequest facilityRequest);

    FacilityResponse updateFaciliyRes(FacilityRequest facilityRequest);

    Facility getFacilityById(String id);

    Facility updateFacility(Facility facility);

    List<Facility> getAllFacilities();

    List<Facility> getAvailableFacilities(Date startDate, Date endDate);

    List<Facility> getUnavailableFacilities(Date startDate, Date endDate);

    List<Facility> getAvailableFacilitiesByName(String name, Date startDate, Date endDate);

    List<Facility> getUnavailableFacilitiesByName(String name, Date startDate, Date endDate);

    List<Facility> getAvailableFacilitiesByType(String typeId, Date startDate, Date endDate);

    List<Facility> getUnavailableFacilitiesByType(String typeId, Date startDate, Date endDate);


//    List<Facility> getFacilitiesByType(String typeId);
//
//    List<Facility> getFacilityByName(String name);

    void deleteFacility(String id);

}
