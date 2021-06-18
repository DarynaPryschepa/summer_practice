package com.summer_practice.demo.RepositoryTesting;

import com.summer_practice.demo.entities.WorkPlace;
import com.summer_practice.demo.repositories.WorkPlaceRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static junit.framework.TestCase.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WPlaceTesting {
    @Autowired
    private WorkPlaceRepository wplaceRepository;

    @Test
    public void addWPlace() {
        WorkPlace workPlace = new WorkPlace();
        Timestamp data = Timestamp.valueOf(LocalDateTime.now());
        workPlace.setCreatedAt(data);
        workPlace.setCreatedBy("Nikita");
        workPlace.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        workPlace.setUpdatedBy("Nikita");
        workPlace.setName("Nikita");
        workPlace.setCity("Chernihiv");

        wplaceRepository.save(workPlace);
        assertEquals("Nikita", wplaceRepository.findById(Long.valueOf("9")).get().getCreatedBy());
        assertTrue(wplaceRepository.findById(Long.valueOf("9")).isPresent());
    }

    @Test
    public void updateMonitor() {

        WorkPlace workPlace = wplaceRepository.findById(Long.valueOf("9")).get();
        workPlace.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        workPlace.setUpdatedBy("admin");
        wplaceRepository.save(workPlace);

        assertEquals("admin", wplaceRepository.findById(Long.valueOf("9")).get().getUpdatedBy());
    }

    @Test
    public void delMonitor() {
        wplaceRepository.deleteById(Long.valueOf("9"));
        assertFalse(wplaceRepository.findById(Long.valueOf("9")).isPresent());
    }

    @Test
    public void findCPUTesting() {
        assertEquals(1, wplaceRepository.findByName("W").size());
    }

    @Test
    public void findHDDtesting() {
        assertEquals(1, wplaceRepository.findByCity("Chernihiv").size());
    }


}
