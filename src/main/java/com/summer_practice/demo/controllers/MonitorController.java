package com.summer_practice.demo.controllers;

import com.summer_practice.demo.entities.Monitor;
import com.summer_practice.demo.entities.WorkPlace;
import com.summer_practice.demo.services.MonitorService;
import com.summer_practice.demo.services.WorkPlaceService;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MonitorController {
    private MonitorService monitorService;
    private WorkPlaceService workPlaceService;

    MonitorController(MonitorService monitorService, WorkPlaceService workPlaceService) {
        this.monitorService = monitorService;
        this.workPlaceService = workPlaceService;
    }

    @GetMapping("/monitors")
    ResponseEntity<List<Monitor>> getAllMonitors() {
        return ResponseEntity.ok().cacheControl(CacheControl.noCache()).body(monitorService.findAllMonitors());
    }

    @GetMapping("/monitorsbyvesa/{vesa}")
    List<Monitor> getAllMonitorsbyVesa(@PathVariable String vesa) {
        return monitorService.findByVesa(vesa);
    }

    @GetMapping("/monitorsbyh/{h1}/{h2}")
    List<Monitor> getAllMonitorsbyHeigths(@PathVariable int h1, @PathVariable int h2) {
        return monitorService.findbyHeightsBetween(h1, h2);
    }

    @GetMapping("/monitorsbyid/{id}")
    Monitor getMonitorById(@PathVariable Long id) {
        return monitorService.findMonitorById(id);
    }

    @PostMapping("workplaces/{idWp}/monitors")
    ResponseEntity<Monitor> addNewMonitor(@RequestBody Monitor newMonitor, @PathVariable Long idWp) {
        WorkPlace wp = workPlaceService.findWorkingPlaceById(idWp);
        if (wp != null) {
            return ResponseEntity.ok().body(monitorService.addMonitor(newMonitor));
        }
        return null;
    }

    @PutMapping("workplaces/{idWp}/monitors/{idMon}")
    Monitor updateMonitor(@RequestBody Monitor newMonitor, @PathVariable Long idWp, @PathVariable Long idMon) {
        Monitor oldMonitor = monitorService.findMonitorById(idMon);
        if (oldMonitor != null) {
            oldMonitor.setCreatedBy(newMonitor.getCreatedBy());
            oldMonitor.setUpdatedBy(newMonitor.getUpdatedBy());
            oldMonitor.setLength(newMonitor.getLength());
            oldMonitor.setHeight(newMonitor.getHeight());
            oldMonitor.setWidth(newMonitor.getWidth());
            oldMonitor.setVesa(newMonitor.getVesa());
            oldMonitor.setDisplaySize(newMonitor.getDisplaySize());
            WorkPlace wp = workPlaceService.findWorkingPlaceById(idWp);
            if (wp != null) {
                oldMonitor.setwPlace(newMonitor.getwPlace());
                return monitorService.updateMonitor(oldMonitor);
            } else {
                return null;
            }
        } else {
            return monitorService.addMonitor(newMonitor);
        }
    }

    @DeleteMapping("/monitors/{id}")
    boolean deleteMonitor(@PathVariable Long id) {
        return monitorService.deleteMonitor(id);
    }
}
