package com.enigmacamp.reservationcampus.services.facilities.impl;

import com.enigmacamp.reservationcampus.model.entity.constant.TypeFacilities;
import com.enigmacamp.reservationcampus.repository.TypeFacilitiesRepository;
import com.enigmacamp.reservationcampus.services.facilities.TypeFacilitiesService;
import com.enigmacamp.reservationcampus.utils.constant.ETypeFacilities;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TypeFacilitiesServiceImpl implements TypeFacilitiesService {
    private final TypeFacilitiesRepository typeFacilitiesRepository;


    @Override
    public TypeFacilities save(TypeFacilities typeFacilities) {
        return typeFacilitiesRepository.save(typeFacilities);
    }

    @Override
    public TypeFacilities getByName(ETypeFacilities name) {
        if(typeFacilitiesRepository.findByName(name).isPresent()){
            return typeFacilitiesRepository.findByName(name).get();
        }else{
            throw new RuntimeException("TypeFacilities name not found");
        }

    }

    @Override
    public TypeFacilities update(TypeFacilities typeFacilities) {
        if(typeFacilitiesRepository.findById(typeFacilities.getId()).isPresent()){
            return typeFacilitiesRepository.save(typeFacilities);
        }else{
            throw new RuntimeException("TypeFacilities name not found");
        }
    }

    @Override
    public void delete(String id) {
        if(typeFacilitiesRepository.findById(id).isPresent()){
            typeFacilitiesRepository.deleteById(id);
        }else{
            throw new RuntimeException("TypeFacilities name not found");
        }
    }

    @Override
    public List<TypeFacilities> getAll() {
        return typeFacilitiesRepository.findAll();
    }
}
