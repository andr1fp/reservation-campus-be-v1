package com.enigmacamp.reservationcampus.services;

import com.enigmacamp.reservationcampus.model.entity.constant.Availability;
import com.enigmacamp.reservationcampus.model.entity.constant.TypeFacilities;
import com.enigmacamp.reservationcampus.utils.constant.EAvailability;
import com.enigmacamp.reservationcampus.utils.constant.ETypeFacilities;

import java.util.List;

public interface AvailabilityService {
    Availability save(Availability availability);
    Availability update(Availability availability);
    Availability getByName(EAvailability name);
    List<Availability> getAllAvailability();
    void delete(String id);
}
