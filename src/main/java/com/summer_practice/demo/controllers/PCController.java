package com.summer_practice.demo.controllers;

import com.summer_practice.demo.entities.PC;
import com.summer_practice.demo.services.PCService;
import com.summer_practice.demo.services.WorkPlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PCController {
  private PCService pcService;

  @Autowired
  PCController(PCService pcService) {
    this.pcService = pcService;
  }

  @GetMapping("/pcs")
  ResponseEntity<List<PC>> getAllPCs() {
    return new ResponseEntity<>(pcService.findAllPCs(), HttpStatus.OK);
  }

  @GetMapping("/pcsbyhdd/{hdd}")
  ResponseEntity<List<PC>> getAllPcsByHdd(@PathVariable int hdd) {
    return new ResponseEntity<>(pcService.findAllByHddSize(hdd), HttpStatus.OK);
  }

  @GetMapping("/pcsbycpu/{cpu}")
  ResponseEntity<List<PC>> getAllPcsByCpu(@PathVariable int cpu) {
    return new ResponseEntity<>(pcService.findAllByCpuCount(cpu), HttpStatus.OK);
  }

  @GetMapping("/pcbyid/{id}")
  ResponseEntity<PC> getPCById(@PathVariable Long id) {
    return new ResponseEntity<>(pcService.findPCById(id), HttpStatus.OK);
  }

  @PostMapping("/pc")
  ResponseEntity<PC> addNewPC(@RequestBody PC newPC) {
    return new ResponseEntity<>(pcService.addPC(newPC), HttpStatus.CREATED);
  }

  @PutMapping("/pc")
  ResponseEntity<PC> updatePC(@RequestBody PC newPC) {

    return new ResponseEntity<>(pcService.updatePC(newPC), HttpStatus.OK);
  }

  @DeleteMapping("/pc/{id}")
  ResponseEntity<Boolean> deleteMonitor(@PathVariable Long id) {
    return new ResponseEntity<>(pcService.deletePC(id), HttpStatus.OK);
  }
}
