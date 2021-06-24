package com.summer_practice.demo.controllers;

import com.summer_practice.demo.entities.Monitor;
import com.summer_practice.demo.services.MonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MonitorController {
  private MonitorService monitorService;

  @Autowired
  MonitorController(MonitorService monitorService) {
    this.monitorService = monitorService;
  }

  @GetMapping("/monitors")
  ResponseEntity<List<Monitor>> getAllMonitors() {
    return new ResponseEntity<>(monitorService.findAllMonitors(), HttpStatus.OK);
  }

  @GetMapping("/monitorsbyvesa/{vesa}")
  ResponseEntity<List<Monitor>> getAllMonitorsbyVesa(@PathVariable String vesa) {
    return new ResponseEntity<>(monitorService.findByVesa(vesa), HttpStatus.OK);
  }

  @GetMapping("/monitorsbyh/{h1}/{h2}")
  ResponseEntity<List<Monitor>> getAllMonitorsbyHeigths(
      @PathVariable int h1, @PathVariable int h2) {
    return new ResponseEntity<>(monitorService.findbyHeightsBetween(h1, h2), HttpStatus.OK);
  }

  @GetMapping("/monitorbyid/{id}")
  ResponseEntity<Monitor> getMonitorById(@PathVariable Long id) {
    return new ResponseEntity<>(monitorService.findMonitorById(id), HttpStatus.OK);
  }

  @PostMapping("/monitor")
  ResponseEntity<Monitor> addNewMonitor(@RequestBody Monitor newMonitor) {
    return new ResponseEntity<>(monitorService.addMonitor(newMonitor), HttpStatus.CREATED);
  }

  @PutMapping("/monitor")
  ResponseEntity<Monitor> updateMonitor(@RequestBody Monitor newMonitor) {
    return new ResponseEntity<>(monitorService.updateMonitor(newMonitor), HttpStatus.OK);
  }

  @DeleteMapping("/monitor/{id}")
  ResponseEntity<Boolean> deleteMonitor(@PathVariable Long id) {
    return new ResponseEntity<>(monitorService.deleteMonitor(id), HttpStatus.OK);
  }
}
