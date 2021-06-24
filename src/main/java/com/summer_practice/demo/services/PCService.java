package com.summer_practice.demo.services;

import com.summer_practice.demo.entities.PC;
import com.summer_practice.demo.entities.WorkPlace;
import com.summer_practice.demo.repositories.PCRepository;
import com.summer_practice.demo.repositories.WorkPlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PCService {
  private final PCRepository pcRepo;
  private final WorkPlaceRepository wpRepo;

  @Autowired
  public PCService(PCRepository pcRepo, WorkPlaceRepository wpRepo) {
    this.pcRepo = pcRepo;
    this.wpRepo = wpRepo;
  }

  public PC addPC(PC pc) {
    WorkPlace wp = wpRepo.getById(pc.getwPlacePc().getWplaceId());
    if (wp != null && pc.getCpuCount() >= 2) {
      pc.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
      pc.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
      return pcRepo.save(pc);
    }
    return null;
  }

  public PC updatePC(PC newPC) {
    PC oldPC = pcRepo.getById(newPC.getPcId());
    if (oldPC != null && newPC.getCpuCount() > 2) {
      WorkPlace wp = wpRepo.getById(newPC.getwPlacePc().getWplaceId());
      if (wp != null) {
        oldPC.setCreatedBy(newPC.getCreatedBy());
        oldPC.setUpdatedBy(newPC.getUpdatedBy());
        oldPC.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        oldPC.setLength(newPC.getLength());
        oldPC.setHeight(newPC.getHeight());
        oldPC.setWidth(newPC.getWidth());
        oldPC.setCpuCount(newPC.getCpuCount());
        oldPC.setHddSize(newPC.getHddSize());
        oldPC.setwPlacePc(newPC.getwPlacePc());
        return pcRepo.save(oldPC);
      }
    }
    return null;
  }

  public boolean deletePC(Long id) {
    if (pcRepo.findById(id).isPresent()) {
      pcRepo.deleteById(id);
      return true;
    }
    return false;
  }

  public List<PC> findAllPCs() {
    return pcRepo.findAll();
  }

  public List<PC> findAllByCpuCount(int cpucount) {
    return pcRepo.findByCpuCount(cpucount);
  }

  public List<PC> findAllByHddSize(int hdd) {
    return pcRepo.findByHddSize(hdd);
  }

  public PC findPCById(Long id) {
    if (pcRepo.findById(id).isPresent()) {
      return pcRepo.findById(id).get();
    }
    return null;
  }
}
