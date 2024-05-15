package com.enigmacamp.reservationcampus.services.impl;

import com.enigmacamp.reservationcampus.model.entity.constant.Availability;
import com.enigmacamp.reservationcampus.repository.AvailabilityRepository;
import com.enigmacamp.reservationcampus.services.AvailabilityService;
import com.enigmacamp.reservationcampus.utils.constant.EAvailability;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AvailabilityServiceImpl implements AvailabilityService {

    private final AvailabilityRepository availabilityRepository;

    @Override
    public Availability save(Availability availability) {
        return availabilityRepository.save(availability);
    }

    @Override
    public Availability getByName(EAvailability name) {
        if(availabilityRepository.findByName(name).isPresent()){
            return availabilityRepository.findByName(name).get();
        }else{
            throw new RuntimeException("Availability name not found");
        }
    }

    @Override
    public List<Availability> getAll() {
        return availabilityRepository.findAll();
    }

    @Override
    public Availability update(Availability availability) {
        if(availabilityRepository.findById(availability.getId()).isPresent()){
            return availabilityRepository.save(availability);
        }else{
            throw new RuntimeException("Availability name not found");
        }
    }

    @Override
    public void delete(String id) {
        if(availabilityRepository.findById(id).isPresent()){
            availabilityRepository.deleteById(id);
        }else{
            throw new RuntimeException("Availability name not found");
        }
    }
}
