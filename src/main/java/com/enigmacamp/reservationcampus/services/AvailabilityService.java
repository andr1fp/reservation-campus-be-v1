package com.enigmacamp.reservationcampus.services;

import com.enigmacamp.reservationcampus.model.entity.constant.Availability;
import com.enigmacamp.reservationcampus.utils.constant.EAvailability;

import java.util.List;

public interface AvailabilityService {

    void initAvailability();
    List<Availability> getAll();

}
