package com.enigmacamp.reservationcampus.services.impl;

import com.enigmacamp.reservationcampus.model.entity.Facility;
import com.enigmacamp.reservationcampus.model.entity.constant.Availability;
import com.enigmacamp.reservationcampus.model.entity.constant.TypeFacilities;
import com.enigmacamp.reservationcampus.model.request.FacilityRequest;
import com.enigmacamp.reservationcampus.repository.FacilityRepository;
import com.enigmacamp.reservationcampus.services.FacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        facilityRepository.save(facility);
        return facilityRequest;
    }

    @Override
    public Facility getFacilityById(String id) {
        return facilityRepository.findById(id).get();
    }

    @Override
    public Facility updateFacility(Facility facility) {
        if(facilityRepository.findById(facility.getId()).isPresent()){
            Availability availability = Availability.builder().id(facility.getAvailability().getId()).build();
            TypeFacilities typeFacilities = TypeFacilities.builder().id(facility.getTypeFacilities().getId()).build();
            Facility facilities = new Facility();

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
    public List<Facility> getFacilityByName(String name) {
        return null;
    }

    @Override
    public List<Facility> getFacilitiesByType(String type) {
        return null;
    }

    @Override
    public void deleteFacility(String id) {
        facilityRepository.findById(id).get();
    }
}
