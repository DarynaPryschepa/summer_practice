package com.summer_practice.demo.repositories;

import com.summer_practice.demo.entities.Monitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MonitorRepository extends JpaRepository<Monitor, Long> {
  // Finding monitors with height in the range of h1 and h2 params
  @Query("SELECT m FROM Monitor m WHERE m.height >= ?1 AND m.height <= ?2")
  List<Monitor> findByHeightBetween(int h1, int h2);

  // Finding monitors vesa  of wich ends on paramOfVesa param
  @Query("SELECT m FROM Monitor m WHERE m.vesa like %:vesa ")
  List<Monitor> findByVesaContains(@Param("vesa") String paramOfVesa);
}
