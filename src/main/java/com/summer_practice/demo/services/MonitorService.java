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
  private final MonitorRepository monitorRepo;
  private final WorkPlaceRepository wpRepo;

  @Autowired
  public MonitorService(MonitorRepository monitorRepo, WorkPlaceRepository wpRepo) {
    this.monitorRepo = monitorRepo;
    this.wpRepo = wpRepo;
  }

  public Monitor addMonitor(Monitor mon) {
    mon.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
    mon.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
    WorkPlace wp = wpRepo.getById(mon.getwPlace().getWplaceId());
    if (wp != null) {
      return monitorRepo.save(mon);
    }
    return null;
  }

  public Monitor updateMonitor(Monitor newMonitor) {
    Monitor oldMonitor = monitorRepo.getById(newMonitor.getMonId());
    if (oldMonitor != null) {
      oldMonitor.setCreatedBy(newMonitor.getCreatedBy());
      oldMonitor.setUpdatedBy(newMonitor.getUpdatedBy());
      oldMonitor.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
      oldMonitor.setLength(newMonitor.getLength());
      oldMonitor.setHeight(newMonitor.getHeight());
      oldMonitor.setWidth(newMonitor.getWidth());
      oldMonitor.setVesa(newMonitor.getVesa());
      oldMonitor.setDisplaySize(newMonitor.getDisplaySize());
      WorkPlace wp = wpRepo.getById(newMonitor.getwPlace().getWplaceId());
      if (wp != null) {
        oldMonitor.setwPlace(newMonitor.getwPlace());
        return monitorRepo.save(oldMonitor);
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
