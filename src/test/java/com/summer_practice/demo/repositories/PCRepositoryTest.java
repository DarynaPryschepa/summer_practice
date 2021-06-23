package com.summer_practice.demo.repositories;

import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.summer_practice.demo.entities.PC;
import com.summer_practice.demo.entities.WorkPlace;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PCRepositoryTest {

  @Autowired private PCRepository pcRepository;
  @Autowired private WorkPlaceRepository wplaceRepository;

  @Test
  @ExpectedDataSet(
      value = {"datasets/pcExpectedForAdding.yml"},
      ignoreCols = {"pc_id"})
  public void addPCTesting() {
    PC pcTest = new PC();
    Timestamp data = Timestamp.valueOf("2021-06-18 12:37:49.088739");
    pcTest.setCreatedAt(data);
    pcTest.setCreatedBy("Asus");
    pcTest.setUpdatedAt(Timestamp.valueOf("2021-06-18 12:37:49.088859"));
    pcTest.setUpdatedBy("Ihor");

    pcTest.setLength(242);
    pcTest.setHeight(466);
    pcTest.setWidth(622);
    pcTest.setCpuCount(4);
    pcTest.setHddSize(400);
    assertTrue(wplaceRepository.findById(1L).isPresent());
    WorkPlace w = wplaceRepository.findById(1L).get();
    pcTest.setwPlacePc(w);
    pcRepository.save(pcTest);
  }

  @Test
  @ExpectedDataSet(value = {"datasets/pcExpectedForDefault.yml"})
  public void delPCTesting() {
    pcRepository.deleteById(20L);
  }

  @Test
  @ExpectedDataSet(value = {"datasets/pcExpectedForDefault.yml"})
  public void findCPUTesting() {
    assertEquals(1, pcRepository.findByCpuCount(4).size());
  }

  @Test
  @ExpectedDataSet(value = {"datasets/pcExpectedForDefault.yml"})
  public void findHddTesting() {
    assertEquals(1, pcRepository.findByHddSize(700).size());
  }

  @Test
  @ExpectedDataSet(value = {"datasets/pcExpectedForUpdate.yml"})
  public void updatePCTesting() {
    assertTrue(pcRepository.findById(1L).isPresent());
    PC pcTest = pcRepository.findById(1L).get();
    pcTest.setUpdatedBy("admin");
    pcRepository.save(pcTest);
  }
}
