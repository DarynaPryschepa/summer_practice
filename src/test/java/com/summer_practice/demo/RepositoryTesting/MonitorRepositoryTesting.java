package com.summer_practice.demo.RepositoryTesting;

import com.summer_practice.demo.entities.Monitor;
import com.summer_practice.demo.entities.WorkPlace;
import com.summer_practice.demo.repositories.MonitorRepository;
import com.summer_practice.demo.repositories.WorkPlaceRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static junit.framework.TestCase.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MonitorRepositoryTesting {
    @Autowired
    private MonitorRepository monitorRepository;
    @Autowired
    private WorkPlaceRepository wplaceRepository;

    @Test
    public void addMonitor() {
        Monitor monitorTest = new Monitor();
        Timestamp data = Timestamp.valueOf(LocalDateTime.now());
        monitorTest.setCreatedAt(data);
        monitorTest.setCreatedBy("Asus");
        monitorTest.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        monitorTest.setUpdatedBy("Ihor");

        monitorTest.setLength(242);
        monitorTest.setHeight(466);
        monitorTest.setWidth(622);
        monitorTest.setVesa("75x75mm");
        monitorTest.setDisplay_size("27" + '"');
        WorkPlace w = wplaceRepository.findById(Long.valueOf("1")).get();
        monitorTest.setwPlace(w);
        monitorRepository.save(monitorTest);
        assertEquals("Asus", monitorRepository.findById(Long.valueOf("6")).get().getCreatedBy());
        assertTrue(monitorRepository.findById(Long.valueOf("6")).isPresent());
    }

    @Test
    public void delMonitor() {
        monitorRepository.deleteById(Long.valueOf("6"));
        assertFalse(monitorRepository.findById(Long.valueOf("6")).isPresent());
    }

    @Test
    public void updateMonitor() {

        Monitor monitorTest = monitorRepository.findById(Long.valueOf("1")).get();
        monitorTest.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        monitorTest.setUpdatedBy("admin");
        monitorRepository.save(monitorTest);

        assertEquals("admin", monitorRepository.findById(Long.valueOf("1")).get().getUpdatedBy());
    }

    @Test
    public void findheightTesting() {
        assertEquals(1, monitorRepository.findByHeightBetween(700, 800).size());
    }

    @Test
    public void findVesaTesting() {
        assertEquals(1, monitorRepository.findByVesaContains("800").size());
    }

}
