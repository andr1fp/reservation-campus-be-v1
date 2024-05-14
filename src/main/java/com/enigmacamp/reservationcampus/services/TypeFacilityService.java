package com.enigmacamp.reservationcampus.services;

import com.enigmacamp.reservationcampus.model.entity.constant.TypeFacilities;
import com.enigmacamp.reservationcampus.utils.constant.ETypeFacilities;

import java.util.List;

public interface TypeFacilityService {
    TypeFacilities save(TypeFacilities typeFacilities);
    TypeFacilities update(TypeFacilities typeFacilities);
    TypeFacilities getByName(ETypeFacilities name);
    List<TypeFacilities> getAllTypes();
    void delete(String id);
}
