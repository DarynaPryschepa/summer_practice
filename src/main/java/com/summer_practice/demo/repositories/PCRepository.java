package com.summer_practice.demo.repositories;

import com.summer_practice.demo.entities.PC;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PCRepository extends CrudRepository<PC,Long> {
    List<PC> findByCpuCount(int cpu);

    List<PC> findByHddSize(int hdd);
}
