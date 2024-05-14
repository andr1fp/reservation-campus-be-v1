package com.enigmacamp.reservationcampus.services.impl;

import com.enigmacamp.reservationcampus.model.entity.constant.TypeFacilities;
import com.enigmacamp.reservationcampus.repository.TypeFacilityRepository;
import com.enigmacamp.reservationcampus.services.TypeFacilityService;
import com.enigmacamp.reservationcampus.utils.constant.ETypeFacilities;
import com.enigmacamp.reservationcampus.utils.exception.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TypeFacilityServiceImpl implements TypeFacilityService {
    private final TypeFacilityRepository typeFacilityRepository;
    @Override
    public TypeFacilities save(TypeFacilities typeFacilities) {
        return typeFacilityRepository.save(typeFacilities);
    }

    @Override
    public TypeFacilities update(TypeFacilities typeFacilities) {
        if (typeFacilityRepository.findById(typeFacilities.getId()).isPresent()) {
            return typeFacilityRepository.save(typeFacilities);
        }
        else {
            throw new DataNotFoundException("Data Tidak Ditemukan");
        }
    }

    @Override
    public TypeFacilities getByName(ETypeFacilities name) {
        if (typeFacilityRepository.findByName(name).isPresent()) {
            return typeFacilityRepository.findByName(name).get();
        }
        else {
            throw new DataNotFoundException("Data Tidak Ditemukan");
        }
    }

    @Override
    public List<TypeFacilities> getAllTypes() {
        return typeFacilityRepository.findAll();
    }

    @Override
    public void delete(String id) {
        if (typeFacilityRepository.findById(id).isPresent()) {
            typeFacilityRepository.deleteById(id);
        }
        else {
            throw new DataNotFoundException("Data Tidak Ditemukan");
        }
    }
}

