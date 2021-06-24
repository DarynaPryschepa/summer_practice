package com.summer_practice.demo.repositories;

import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.spring.api.DBRider;
import com.summer_practice.demo.entities.Monitor;
import com.summer_practice.demo.entities.WorkPlace;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;

import static junit.framework.TestCase.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@DBRider
public class MonitorRepositoryTest {

  @Autowired private MonitorRepository monitorRepository;
  @Autowired private WorkPlaceRepository wplaceRepository;

  @Test
  @ExpectedDataSet(
      value = {"datasets/monitorExpectedForAdding.yml"},
      ignoreCols = {"mon_id"})
  public void addMonitorTesting() {
    Monitor monitorTest = new Monitor();
    monitorTest.setCreatedAt(Timestamp.valueOf("2021-06-18 12:37:49.088739"));
    monitorTest.setCreatedBy("Asus");
    monitorTest.setUpdatedAt(Timestamp.valueOf("2021-06-18 12:37:49.088859"));
    monitorTest.setUpdatedBy("Ihor");

    monitorTest.setLength(242);
    monitorTest.setHeight(466);
    monitorTest.setWidth(622);
    monitorTest.setVesa("75x75mm");
    monitorTest.setDisplaySize("27" + "''");
    assertTrue(wplaceRepository.findById(1L).isPresent());
    WorkPlace w = wplaceRepository.findById(1L).get();
    monitorTest.setwPlace(w);
    monitorRepository.save(monitorTest);
  }

  @Test
  @ExpectedDataSet(value = {"datasets/monitorExpectedForUpdate.yml"})
  public void updateMonitorTesting() {
    assertTrue(monitorRepository.findById(1L).isPresent());
    Monitor monitorTest = monitorRepository.findById(1L).get();
    monitorTest.setUpdatedBy("admin");
    monitorRepository.save(monitorTest);
  }

  @Test
  @ExpectedDataSet(value = {"datasets/monitorDefaultPattern.yml"})
  public void delMonitorTesting() {
    monitorRepository.deleteById(17L);
  }

  @Test
  @ExpectedDataSet(value = {"datasets/monitorDefaultPattern.yml"})
  public void findHeightTesting() {
    monitorRepository.findByHeightBetween(700, 800);
  }

  @Test
  @ExpectedDataSet(value = {"datasets/monitorDefaultPattern.yml"})
  public void findVesaTesting() {
    monitorRepository.findByVesaContains("800");
  }
}
