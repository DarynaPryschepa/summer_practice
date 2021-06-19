package com.summer_practice.demo.services;

import com.summer_practice.demo.entities.WorkPlace;
import com.summer_practice.demo.repositories.WorkPlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class WorkPlaceService {
    @Autowired
    WorkPlaceRepository wpRepo;

    public WorkPlace addWorkPlace(String createdBy, String updatedBy, String name, String city) {
        WorkPlace workplace = new WorkPlace();
        Timestamp data = Timestamp.valueOf(LocalDateTime.now());
        workplace.setCreatedAt(data);
        workplace.setCreatedBy(createdBy);
        workplace.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        workplace.setUpdatedBy(updatedBy);
        workplace.setName(name);
        workplace.setCity(city);
        if (city.length() <= 3) {
            return null;
        }
        return wpRepo.save(workplace);
    }

    public WorkPlace updateWorkPlace(Long id_Wp, String createdBy, String ubdatedBy, String name, String city) {
        if (wpRepo.findById(id_Wp).isPresent()) {
            WorkPlace workplace = wpRepo.findById(id_Wp).get();
            //workplace.setCreatedAt(data); No one can change date of creation
            workplace.setCreatedBy(createdBy);
            workplace.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
            workplace.setUpdatedBy(ubdatedBy);
            workplace.setName(name);
            workplace.setCity(city);
            return wpRepo.save(workplace);
        }
        return null;
    }

    public boolean deleteWorkPlace(Long id) {
        if (wpRepo.findById(id).isPresent()) {
            wpRepo.deleteById(id);
            return true;
        }
        return false;
    }

    public List<WorkPlace> findAllWorkingPlaces() {
        return wpRepo.findAll();
    }

    public List<WorkPlace> findAllWorkingPlacesbyCity(String city) {
        return wpRepo.findByCity(city);
    }

    public List<WorkPlace> findAllWorkingPlacesbyName(String name) {
        return wpRepo.findByName(name);
    }

    public WorkPlace findWorkingPlaceById(Long id) {
        if (wpRepo.findById(id).isPresent()) {
            return wpRepo.findById(id).get();
        }
        return null;
    }
}