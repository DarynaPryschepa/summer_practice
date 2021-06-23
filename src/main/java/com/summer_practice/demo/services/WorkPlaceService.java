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

    public WorkPlace addWorkPlace(WorkPlace workPlace) {
        workPlace.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        workPlace.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        if (workPlace.getCity().length() <= 3 || workPlace.getName().length() <= 3) {
            return null;
        }
        return wpRepo.save(workPlace);
    }

    public WorkPlace updateWorkPlace(WorkPlace workPlace) {
        if (workPlace.getCity().length() <= 3 || workPlace.getName().length() <= 3) {
            return null;
        }
        workPlace.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        return wpRepo.save(workPlace);
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