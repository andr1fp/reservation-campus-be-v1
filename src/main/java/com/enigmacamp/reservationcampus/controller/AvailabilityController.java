package com.enigmacamp.reservationcampus.controller;

import com.enigmacamp.reservationcampus.model.entity.constant.Availability;
import com.enigmacamp.reservationcampus.service.AvailabilityService;
import com.enigmacamp.reservationcampus.utils.constant.APIPath;
import com.enigmacamp.reservationcampus.utils.constant.EAvailability;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(APIPath.BASE_PATH + APIPath.AVAILABILITY)
public class AvailabilityController {
    private final AvailabilityService availabilityService;

    @GetMapping
    public List<Availability> getAvailabilities() {
        return availabilityService.getAll();
    }

    @GetMapping("/{name}")
    public Availability getByName(@PathVariable String name) {
        return availabilityService.getByName(EAvailability.valueOf(name.toUpperCase()));
    }

    @PostMapping
    public Availability save(@RequestBody Availability availability) {
        return availabilityService.save(availability);
    }

    @PutMapping
    public Availability update(@RequestBody Availability availability) {
        return availabilityService.update(availability);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        availabilityService.delete(id);
    }
}
