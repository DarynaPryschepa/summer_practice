package com.summer_practice.demo.repositories;

import com.summer_practice.demo.entities.PC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PCRepository extends JpaRepository<PC, Long> {
  // Finding of pcs with cpuCount more than cpu param
  @Query("SELECT p FROM PC p WHERE p.cpuCount >=?1 ")
  List<PC> findByCpuCount(int cpu);

  // Finding of pcs with hddCount more than hdd param
  @Query("SELECT p FROM PC p WHERE p.hddSize >=?1 ")
  List<PC> findByHddSize(int hdd);
}
