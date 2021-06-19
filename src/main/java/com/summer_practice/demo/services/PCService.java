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
    @Autowired
    PCRepository pcRepo;
    @Autowired
    WorkPlaceRepository wpRepo;

   public PC addPC(String createdBy, String updatedBy, int length, int height, int width, int hdd, int cpu, Long idWplace) {
        PC pc = new PC();
        Timestamp data = Timestamp.valueOf(LocalDateTime.now());
        pc.setCreatedAt(data);
        pc.setCreatedBy(createdBy);
        pc.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        pc.setUpdatedBy(updatedBy);

        pc.setLength(length);
        pc.setHeight(height);
        pc.setWidth(width);
        pc.setHddSize(hdd);
        pc.setCpuCount(cpu);
        if (wpRepo.findById(idWplace).isPresent()) {
            WorkPlace workPlace = wpRepo.findById(idWplace).get();
            pc.setWplace(workPlace);
            return  pcRepo.save(pc);
        }
        return  null;
    }

    public PC updatePC(Long id, String createdBy, String updatedBy, int length, int height, int width, int hdd, int cpu, Long idWplace) {
        if (pcRepo.findById(id).isPresent()) {
            PC pc = pcRepo.findById(id).get();
            //pc.setCreatedAt(data); No one can change date of creation
            pc.setCreatedBy(createdBy);
            pc.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
            pc.setUpdatedBy(updatedBy);

            pc.setLength(length);
            pc.setHeight(height);
            pc.setWidth(width);
            pc.setHddSize(hdd);
            pc.setCpuCount(cpu);
            if (wpRepo.findById(idWplace).isPresent()) {
                WorkPlace workPlace = wpRepo.findById(idWplace).get();
                pc.setWplace(workPlace);
                return pcRepo.save(pc);
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
