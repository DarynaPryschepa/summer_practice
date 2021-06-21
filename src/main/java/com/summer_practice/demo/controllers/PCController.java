package com.summer_practice.demo.controllers;

import com.summer_practice.demo.entities.PC;
import com.summer_practice.demo.entities.WorkPlace;
import com.summer_practice.demo.services.PCService;
import com.summer_practice.demo.services.WorkPlaceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PCController {
    private PCService pcService;
    private WorkPlaceService workPlaceService;

    PCController(PCService pcService, WorkPlaceService workPlaceService) {
        this.pcService = pcService;
        this.workPlaceService = workPlaceService;
    }

    @GetMapping("/pcs")
    List<PC> getAllPCs() {
        return pcService.findAllPCs();
    }

    @GetMapping("/pcsbyhdd/{hdd}")
    List<PC> getAllPcsByHdd(@PathVariable int hdd) {
        return pcService.findAllByHddSize(hdd);
    }

    @GetMapping("/pcsbycpu/{cpu}")
    List<PC> getAllPcsByCpu(@PathVariable int cpu) {
        return pcService.findAllByCpuCount(cpu);
    }

    @GetMapping("/pcsbyid/{id}")
    PC getPCById(@PathVariable Long id) {
        return pcService.findPCById(id);
    }

    @PostMapping("workplaces/{idWp}/pcs")
    PC addNewPC(@RequestBody PC newPC, @PathVariable Long idWp) {
        WorkPlace wp = workPlaceService.findWorkingPlaceById(idWp);
        if (wp != null) {
            return pcService.addPC(newPC);
        }
        return null;
    }

    @PutMapping("workplaces/{idWp}/pcs/{idPc}")
    PC updatePC(@RequestBody PC newPC, @PathVariable Long idWp, @PathVariable Long idPc) {
        PC oldPC = pcService.findPCById(idPc);
        if (oldPC != null) {
            oldPC.setCreatedBy(newPC.getCreatedBy());
            oldPC.setUpdatedBy(newPC.getUpdatedBy());
            oldPC.setLength(newPC.getLength());
            oldPC.setHeight(newPC.getHeight());
            oldPC.setWidth(newPC.getWidth());
            oldPC.setCpuCount(newPC.getCpuCount());
            oldPC.setHddSize(newPC.getHddSize());
            WorkPlace wp = workPlaceService.findWorkingPlaceById(idWp);
            if (wp != null) {
                oldPC.setWplace(newPC.getWplace());
                return pcService.updatePC(oldPC);
            } else {
                return null;
            }
        } else {
            return pcService.addPC(newPC);
        }
    }

    @DeleteMapping("/pcs/{id}")
    boolean deleteMonitor(@PathVariable Long id) {
        return pcService.deletePC(id);
    }
}
