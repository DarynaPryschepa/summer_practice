package com.summer_practice.demo.repositories;

import com.github.database.rider.core.api.dataset.ExpectedDataSet;
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
public class WorkPlaceRepositoryTest {

  @Autowired private WorkPlaceRepository wplaceRepository;

  @Test
  @ExpectedDataSet(
      value = {"datasets/workplaceExpectedForAdding.yml"},
      ignoreCols = {"wp_id"})
  public void addWorkingPlaceTesting() {
    WorkPlace workPlace = new WorkPlace();
    Timestamp data = Timestamp.valueOf("2021-06-18 12:37:49.088739");
    workPlace.setCreatedAt(data);
    workPlace.setCreatedBy("Nikita");
    workPlace.setUpdatedAt(Timestamp.valueOf("2021-06-18 12:37:49.088859"));
    workPlace.setUpdatedBy("Nikita");
    workPlace.setName("Working_Place_321");
    workPlace.setCity("Chernihiv");
    wplaceRepository.save(workPlace);
  }

  @Test
  @ExpectedDataSet(value = {"datasets/workplaceDefaultPattern.yml"})
  public void delWorkingPlaceTesting() {
    wplaceRepository.deleteById(20L);
  }

  @Test
  @ExpectedDataSet(value = {"datasets/workplaceDefaultPattern.yml"})
  public void findByNameTesting() {
    wplaceRepository.findByName("W");
  }

  @Test
  @ExpectedDataSet(value = {"datasets/workplaceDefaultPattern.yml"})
  public void findFromCityTesting() {
    wplaceRepository.findByCity("Chernihiv");
  }

  @Test
  @ExpectedDataSet(value = {"datasets/workplaceExpectedForUpdating.yml"})
  public void updateWorkingPlaceTesting() {
    assertTrue(wplaceRepository.findById(1L).isPresent());
    WorkPlace workPlace = wplaceRepository.findById(1L).get();
    workPlace.setUpdatedBy("admin");
    wplaceRepository.save(workPlace);
  }
}
