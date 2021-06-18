package com.summer_practice.demo.repositories;

import com.summer_practice.demo.entities.WorkPlace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkPlaceRepository extends JpaRepository<WorkPlace, Long> {
    // Finding of working places  name  of which starts  from  name param
    @Query("SELECT wp FROM WorkPlace wp WHERE wp.name like :name% ")
    List<WorkPlace> findByName(@Param("name") String name);

    // Finding of working places with city equals to city param
    @Query("SELECT wp FROM WorkPlace wp WHERE wp.city=?1")
    List<WorkPlace> findByCity(String city);
}
