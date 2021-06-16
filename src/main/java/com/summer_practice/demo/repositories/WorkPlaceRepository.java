package com.summer_practice.demo.repositories;

import com.summer_practice.demo.entities.WorkPlace;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkPlaceRepository extends CrudRepository<WorkPlace,Long> {
    List<WorkPlace> findByName(String name);

    List<WorkPlace> findByCity(String city);
}
