package com.summer_practice.demo.services;

import com.summer_practice.demo.entities.Monitor;
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

    public Monitor addMonitor(Monitor mon) {

        mon.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        mon.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        return monitorRepo.save(mon);
    }

    public Monitor updateMonitor(Monitor mon) {
        mon.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        return monitorRepo.save(mon);
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
        if (h1 > h2) {
            return null;
        }
        return monitorRepo.findByHeightBetween(h1, h2);
    }

    public List<Monitor> findByVesa(String vesa) {
        return monitorRepo.findByVesaContains(vesa);
    }

    public Monitor findMonitorById(Long id) {
        if (monitorRepo.findById(id).isPresent()) {
            return monitorRepo.findById(id).get();
        }
        return null;
    }
}
