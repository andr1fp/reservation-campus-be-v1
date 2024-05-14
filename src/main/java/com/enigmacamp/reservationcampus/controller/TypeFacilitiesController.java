package com.enigmacamp.reservationcampus.controller;

import com.enigmacamp.reservationcampus.model.entity.constant.TypeFacilities;
import com.enigmacamp.reservationcampus.service.TypeFacilitiesService;
import com.enigmacamp.reservationcampus.utils.constant.APIPath;
import com.enigmacamp.reservationcampus.utils.constant.ETypeFacilities;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(APIPath.BASE_PATH + APIPath.TYPE_FACILITIES)
public class TypeFacilitiesController {
    private final TypeFacilitiesService typeFacilitiesService;

    @GetMapping
    public List<TypeFacilities> getAllTypeFacilities() {
        return typeFacilitiesService.getAll();
    }

    @GetMapping("/{name}")
    public TypeFacilities getTypeFacilitiesByName(@PathVariable String name) {
        return typeFacilitiesService.getByName(ETypeFacilities.valueOf(name.toUpperCase()));
    }

    @PostMapping
    public TypeFacilities saveTypeFacilities(@RequestBody TypeFacilities typeFacilities) {
        return typeFacilitiesService.save(typeFacilities);
    }

    @PutMapping
    public TypeFacilities updateTypeFacilities(@RequestBody TypeFacilities typeFacilities) {
        return typeFacilitiesService.update(typeFacilities);
    }

    @DeleteMapping("/{id}")
    public void deleteTypeFacilities(@PathVariable String id) {
        typeFacilitiesService.delete(id);
    }
}
