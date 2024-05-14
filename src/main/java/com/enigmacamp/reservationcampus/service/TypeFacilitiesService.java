package com.enigmacamp.reservationcampus.service;

import com.enigmacamp.reservationcampus.model.entity.constant.TypeFacilities;
import com.enigmacamp.reservationcampus.utils.constant.ETypeFacilities;

import java.util.List;
import java.util.Optional;

public interface TypeFacilitiesService {
    TypeFacilities save(TypeFacilities typeFacilities);
    TypeFacilities getByName(ETypeFacilities name);
    TypeFacilities update(TypeFacilities typeFacilities);
    void delete(String id);
    List<TypeFacilities> getAll();
}
