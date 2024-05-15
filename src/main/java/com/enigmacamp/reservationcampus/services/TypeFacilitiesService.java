package com.enigmacamp.reservationcampus.services;

import com.enigmacamp.reservationcampus.model.entity.constant.TypeFacilities;
import com.enigmacamp.reservationcampus.utils.constant.ETypeFacilities;

import java.util.List;

public interface TypeFacilitiesService {

    void initTypeFacilities();
    List<TypeFacilities> getAll();
}
