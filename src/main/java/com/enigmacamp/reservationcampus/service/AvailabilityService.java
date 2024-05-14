package com.enigmacamp.reservationcampus.service;

import com.enigmacamp.reservationcampus.model.entity.constant.Availability;
import com.enigmacamp.reservationcampus.utils.constant.EAvailability;

import java.util.List;

public interface AvailabilityService {

    Availability save(Availability availability);
    Availability getByName(EAvailability name);
    List<Availability> getAll();
    Availability update(Availability availability);
    void delete(String id);
}
