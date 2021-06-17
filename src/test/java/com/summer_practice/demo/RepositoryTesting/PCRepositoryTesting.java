package com.summer_practice.demo.RepositoryTesting;

import com.summer_practice.demo.entities.PC;
import com.summer_practice.demo.entities.WorkPlace;
import com.summer_practice.demo.repositories.PCRepository;
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
public class PCRepositoryTesting {

    @Autowired
    private PCRepository pcRepository;
    @Autowired
    private WorkPlaceRepository wplaceRepository;

    @Test
    public void addPC() {
        PC pcTest = new PC();
        Timestamp data = Timestamp.valueOf(LocalDateTime.now());
        pcTest.setCreatedAt(data);
        pcTest.setCreatedBy("Asus");
        pcTest.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        pcTest.setUpdatedBy("Ihor");

        pcTest.setLength(242);
        pcTest.setHeight(466);
        pcTest.setWidth(622);
        pcTest.setCpuCount(4);
        pcTest.setHddSize(400);
        WorkPlace w = wplaceRepository.findById(Long.valueOf("1")).get();
        pcTest.setWplace(w);
        pcRepository.save(pcTest);
        assertEquals("Asus", pcRepository.findById(Long.valueOf("8")).get().getCreatedBy());
        assertTrue(pcRepository.findById(Long.valueOf("8")).isPresent());
    }

    @Test
    public void updateMonitor() {

        PC pcTest = pcRepository.findById(Long.valueOf("8")).get();
        pcTest.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        pcTest.setUpdatedBy("admin");
        pcRepository.save(pcTest);
        assertEquals("admin", pcRepository.findById(Long.valueOf("8")).get().getUpdatedBy());
    }

    @Test
    public void delMonitor() {
        pcRepository.deleteById(Long.valueOf("8"));
        assertFalse(pcRepository.findById(Long.valueOf("8")).isPresent());
    }

    @Test
    public void findCPUTesting() {
        assertEquals(1, pcRepository.findByCpuCount(4).size());
    }

    @Test
    public void findHDDesting() {
        assertEquals(1, pcRepository.findByHddSize(700).size());
    }

}
