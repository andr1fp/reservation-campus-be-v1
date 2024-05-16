package com.enigmacamp.reservationcampus.services.impl;

import com.enigmacamp.reservationcampus.model.entity.Facility;
import com.enigmacamp.reservationcampus.model.entity.constant.Availability;
import com.enigmacamp.reservationcampus.model.entity.constant.TypeFacilities;
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
    public Facility saveFacility(Facility facility) {
        return facilityRepository.save(facility);
    }

    @Override
    public Facility getFacilityById(String id) {
        return facilityRepository.findById(id).get();
    }

    @Override
    public Facility updateFacility(Facility facility) {
        if(facilityRepository.findById(facility.getId()).isPresent()){
            return facilityRepository.save(facility);
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
        facilityRepository.deleteById(id);
    }
}
