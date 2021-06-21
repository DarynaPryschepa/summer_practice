package com.summer_practice.demo.services;

import com.summer_practice.demo.entities.PC;
import com.summer_practice.demo.repositories.PCRepository;
import com.summer_practice.demo.repositories.WorkPlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PCService {
    @Autowired
    PCRepository pcRepo;
    @Autowired
    WorkPlaceRepository wpRepo;

    public PC addPC(PC pc) {
        pc.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        pc.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        if (pc.getCpuCount() >= 2) {
            return pcRepo.save(pc);
        }
        return null;
    }

    public PC updatePC(PC pc) {
        pc.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        if (pc.getCpuCount() >= 2) {
            return pcRepo.save(pc);
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
