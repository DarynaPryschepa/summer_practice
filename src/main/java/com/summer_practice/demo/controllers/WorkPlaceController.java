package com.summer_practice.demo.controllers;

import com.summer_practice.demo.entities.WorkPlace;
import com.summer_practice.demo.services.WorkPlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WorkPlaceController {

  private WorkPlaceService workPlaceService;

  @Autowired
  WorkPlaceController(WorkPlaceService workPlaceService) {
    this.workPlaceService = workPlaceService;
  }

  @GetMapping("/workplaces")
  ResponseEntity<List<WorkPlace>> getAllWorkingPlaces() {
    return new ResponseEntity<>(workPlaceService.findAllWorkingPlaces(), HttpStatus.OK);
  }

  @GetMapping("/workplacesbycity/{city}")
  ResponseEntity<List<WorkPlace>> getAllWorkingPlacesbyCity(@PathVariable String city) {
    return new ResponseEntity<>(workPlaceService.findAllWorkingPlacesbyCity(city), HttpStatus.OK);
  }

  @GetMapping("/workplacesbyname/{name}")
  ResponseEntity<List<WorkPlace>> getAllWorkingPlacesbyName(@PathVariable String name) {
    return new ResponseEntity<>(workPlaceService.findAllWorkingPlacesbyName(name), HttpStatus.OK);
  }

  @GetMapping("/workplacebyid/{id}")
  ResponseEntity<WorkPlace> getWorkingPlaceById(@PathVariable Long id) {
    return new ResponseEntity<>(workPlaceService.findWorkingPlaceById(id), HttpStatus.OK);
  }

  @PostMapping("/workplace")
  ResponseEntity<WorkPlace> addNewWorkingPlaces(@RequestBody WorkPlace newWorkPlace) {
    return new ResponseEntity<>(workPlaceService.addWorkPlace(newWorkPlace), HttpStatus.CREATED);
  }

  @PutMapping("/workplace")
  ResponseEntity<WorkPlace> updateWorkingPlaces(@RequestBody WorkPlace newWorkPlace) {
    return new ResponseEntity<>(workPlaceService.updateWorkPlace(newWorkPlace), HttpStatus.OK);
  }

  @DeleteMapping("/workplace/{id}")
  ResponseEntity<Boolean> deleteWorkingPlace(@PathVariable Long id) {
    return new ResponseEntity<>(workPlaceService.deleteWorkPlace(id), HttpStatus.OK);
  }
}
