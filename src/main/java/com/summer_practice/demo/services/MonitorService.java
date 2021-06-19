package com.summer_practice.demo.services;

import com.summer_practice.demo.entities.Monitor;
import com.summer_practice.demo.entities.WorkPlace;
import com.summer_practice.demo.repositories.MonitorRepository;
import com.summer_practice.demo.repositories.WorkPlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MonitorService {
    @Autowired
    MonitorRepository monitorRepo;
    @Autowired
    WorkPlaceRepository wpRepo;

    public Monitor addMonitor(String createdBy, String updatedBy, int length, int height, int width, String vesa, String displaySize, Long idWplace) {
        Monitor mon = new Monitor();
        Timestamp data = Timestamp.valueOf(LocalDateTime.now());
        mon.setCreatedAt(data);
        mon.setCreatedBy(createdBy);
        mon.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        mon.setUpdatedBy(updatedBy);

        mon.setLength(length);
        mon.setHeight(height);
        mon.setWidth(width);
        mon.setVesa(vesa);
        mon.setDisplaySize(displaySize);
        if (wpRepo.findById(idWplace).isPresent()) {
            WorkPlace workPlace = wpRepo.findById(idWplace).get();
            mon.setwPlace(workPlace);
            return monitorRepo.save(mon);
        }
        return null;
    }

    public Monitor updateMonitor(Long idMon, String createdBy, String updatedBy, int length, int height, int width, String vesa, String displaySize, Long idWplace) {
        if (monitorRepo.findById(idMon).isPresent()) {
            Monitor mon = monitorRepo.findById(idMon).get();
            Timestamp data = Timestamp.valueOf(LocalDateTime.now());
            mon.setCreatedAt(data);
            //mon.setCreatedBy(createdBy); No one can change date of creation
            mon.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
            mon.setUpdatedBy(updatedBy);

            mon.setLength(length);
            mon.setHeight(height);
            mon.setWidth(width);
            mon.setVesa(vesa);
            mon.setDisplaySize(displaySize);
            if (wpRepo.findById(idWplace).isPresent()) {
                WorkPlace workPlace = wpRepo.findById(idWplace).get();
                mon.setwPlace(workPlace);
                return monitorRepo.save(mon);
            }
        }
        return null;
    }

    public boolean deleteMonitor(Long id) {
        if (monitorRepo.findById(id).isPresent()) {
            monitorRepo.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Monitor> findAllMonitors() {
        return monitorRepo.findAll();
    }

    public List<Monitor> findbyHeightsBetween(int h1, int h2) {
        if(h1>h2){
            return null;
        }
        return monitorRepo.findByHeightBetween(h1, h2);
    }

    public List<Monitor> findByVesa(String vesa) {
        return monitorRepo.findByVesaContains(vesa);
    }
    public Monitor findWorkingPlaceById(Long id) {
        if (monitorRepo.findById(id).isPresent()) {
            return monitorRepo.findById(id).get();
        }
        return null;
    }
}
