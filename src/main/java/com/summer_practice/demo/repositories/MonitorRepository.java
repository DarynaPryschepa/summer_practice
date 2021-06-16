package com.summer_practice.demo.repositories;

import com.summer_practice.demo.entities.Monitor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MonitorRepository extends CrudRepository<Monitor,Long> {
    List<Monitor> findByDisplay_size(String display_size);

    List<Monitor> findByVesa(String vesa);
}
