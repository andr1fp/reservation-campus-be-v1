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
        return facilityRepository.findById(id).get();
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

//    @Override
//    public List<Facility> getFacilityByName(String name) {
//        Optional<Facility> facility = facilityRepository.findByName(name);
//        return facility.map(Collections::singletonList).orElse(Collections.emptyList());
//   }
//
//    @Override
//    public List<Facility> getFacilitiesByType(String type) {
//        Optional<Facility> facility = facilityRepository.findByTypeFacilities(type);
//        return facility.map(Collections::singletonList).orElse(Collections.emptyList());
//    }
//
//    @Override
//    public List<Facility> getFacilitiesByAvailability(String availability) {
//        Optional<Facility> facility = facilityRepository.findByAvailabilityIs(availability);
//        return facility.map(Collections::singletonList).orElse(Collections.emptyList());
//    }

    @Override
    public void deleteFacility(String id) {
        facilityRepository.deleteById(id);
    }
}
