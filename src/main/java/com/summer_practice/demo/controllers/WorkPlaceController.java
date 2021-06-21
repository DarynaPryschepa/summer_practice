package com.summer_practice.demo.controllers;

import com.summer_practice.demo.entities.WorkPlace;
import com.summer_practice.demo.services.WorkPlaceService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WorkPlaceController {
    private WorkPlaceService workPlaceService;

    WorkPlaceController(WorkPlaceService workPlaceService) {
        this.workPlaceService = workPlaceService;
        this.workPlaceService = workPlaceService;
    }

    @GetMapping("/workplaces")
    List<WorkPlace> getAllWorkingPlaces() {
        return workPlaceService.findAllWorkingPlaces();
    }

    @GetMapping("/delete/workplacesbycity/{city}")
    List<WorkPlace> getAllWorkingPlacesbyCity(@PathVariable String city) {
        return workPlaceService.findAllWorkingPlacesbyCity(city);
    }

    @GetMapping("/workplacesbyname/{name}")
    List<WorkPlace> getAllWorkingPlacesbyName(@PathVariable String name) {
        return workPlaceService.findAllWorkingPlacesbyName(name);
    }

    @GetMapping("/workplacesbyid/{id}")
    WorkPlace getWorkingPlaceById(@PathVariable Long id) {
        return workPlaceService.findWorkingPlaceById(id);
    }

    @PostMapping("/workplaces")
    WorkPlace addNewWorkingPlaces(@Validated @RequestBody WorkPlace newWorkPlace) {
        return workPlaceService.addWorkPlace(newWorkPlace);
    }

    @PutMapping("/workplaces/{id}")
    WorkPlace updateWorkingPlaces(@RequestBody WorkPlace newWorkPlace, @PathVariable Long id) {
        WorkPlace oldWorkPlace = workPlaceService.findWorkingPlaceById(id);
        if (oldWorkPlace != null) {
            oldWorkPlace.setCreatedBy(newWorkPlace.getCreatedBy());
            oldWorkPlace.setUpdatedBy(newWorkPlace.getUpdatedBy());
            oldWorkPlace.setCity(newWorkPlace.getCity());
            oldWorkPlace.setName(newWorkPlace.getName());

            return workPlaceService.updateWorkPlace(oldWorkPlace);
        } else {
            return workPlaceService.addWorkPlace(newWorkPlace);
        }
    }

    @DeleteMapping("/workplaces/{id}")
    boolean deleteWorkingPlace(@PathVariable Long id) {
        return workPlaceService.deleteWorkPlace(id);
    }

}
