package com.enigmacamp.reservationcampus.services.impl;

import com.enigmacamp.reservationcampus.model.entity.Facility;
import com.enigmacamp.reservationcampus.model.entity.constant.Availability;
import com.enigmacamp.reservationcampus.model.entity.constant.TypeFacilities;
import com.enigmacamp.reservationcampus.model.request.FacilityRequest;
import com.enigmacamp.reservationcampus.model.response.FacilityResponse;
import com.enigmacamp.reservationcampus.repository.FacilityRepository;
import com.enigmacamp.reservationcampus.services.FacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class FacilityServiceImpl implements FacilityService {

    FacilityRepository facilityRepository;

    @Autowired
    FacilityServiceImpl(FacilityRepository facilityRepository){
        this.facilityRepository = facilityRepository;
    }
    @Override
    public FacilityRequest saveFacility(FacilityRequest facilityRequest) {
        Availability availability = Availability.builder().id(facilityRequest.getAvailability()).build();
        TypeFacilities typeFacilities = TypeFacilities.builder().id(facilityRequest.getTypeFacilities()).build();
        Facility facility = new Facility();

        facility.setName(facilityRequest.getName());
        facility.setInformation(facilityRequest.getInformation());
        facility.setPrice(facilityRequest.getPrice());
        facility.setQuantity(facilityRequest.getQuantity());
        facility.setPicture(facilityRequest.getPicture());
        facility.setAvailability(availability);
        facility.setTypeFacilities(typeFacilities);
        Facility facility1 = facilityRepository.save(facility);
        facilityRequest.setId(facility1.getId());
        return facilityRequest;
    }

    @Override
    public FacilityResponse updateFaciliyRes(FacilityRequest facilityRequest) {
        Facility facility = new Facility();
        facility.setId(facilityRequest.getId());
        facility.setName(facilityRequest.getName());
        facility.setInformation(facilityRequest.getInformation());
        facility.setPrice(facilityRequest.getPrice());
        facility.setQuantity(facilityRequest.getQuantity());
        facility.setPicture(facilityRequest.getPicture());
        facility.setAvailability(Availability.builder().id(facilityRequest.getAvailability()).build());
        facility.setTypeFacilities(TypeFacilities.builder().id(facilityRequest.getTypeFacilities()).build());
        Facility facility1 = facilityRepository.save(facility);
        FacilityResponse facilityResponse = new FacilityResponse();
        facilityResponse.setName(facility1.getName());
        facilityResponse.setTypeFacilities(facility1.getTypeFacilities().getId());
        return facilityResponse;

    }

    @Override
    public Facility getFacilityById(String id) {
        return facilityRepository.findById(id).orElseThrow(() -> new RuntimeException("Facility not found"));

    }

    @Override
    public Facility updateFacility(Facility facility) {
        if(facilityRepository.findById(facility.getId()).isPresent()){
            Availability availability = Availability.builder().id(facility.getAvailability().getId()).build();
            TypeFacilities typeFacilities = TypeFacilities.builder().id(facility.getTypeFacilities().getId()).build();
            Facility facilities = new Facility();

            facilities.setId(facility.getId());
            facilities.setName(facility.getName());
            facilities.setInformation(facility.getInformation());
            facilities.setPrice(facility.getPrice());
            facilities.setQuantity(facility.getQuantity());
            facilities.setPicture(facilities.getPicture());
            facilities.setAvailability(availability);
            facilities.setTypeFacilities(typeFacilities);
            facilityRepository.save(facility);

            return facility;
        }
        return null;
    }

    @Override
    public List<Facility> getAllFacilities() {
        return facilityRepository.findAll();
    }

    @Override
    public List<Facility> getAvailableFacilities(Date startDate, Date endDate) {
        return facilityRepository.findAvailableFacilities(startDate, endDate);
    }

    @Override
    public List<Facility> getUnavailableFacilities(Date startDate, Date endDate) {
        return facilityRepository.findUnavailableFacilities(startDate, endDate);
    }

    @Override
    public List<Facility> getAvailableFacilitiesByName(String name, Date startDate, Date endDate) {
        return facilityRepository.findAvailableFacilitiesByName(name, startDate, endDate);
    }

    public List<Facility> getUnavailableFacilitiesByName(String name, Date startDate, Date endDate) {
        return facilityRepository.findUnavailableFacilitiesByName(name, startDate, endDate);
    }

    public List<Facility> getAvailableFacilitiesByType(String typeId, Date startDate, Date endDate) {
        return facilityRepository.findAvailableFacilitiesByType(typeId, startDate, endDate);
    }

    public List<Facility> getUnavailableFacilitiesByType(String typeId, Date startDate, Date endDate) {
        return facilityRepository.findUnavailableFacilitiesByType(typeId, startDate, endDate);
    }

    @Override
    public void deleteFacility(String id) {
        facilityRepository.deleteById(id);
    }
}
