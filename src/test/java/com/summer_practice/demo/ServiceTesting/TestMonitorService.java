package com.summer_practice.demo.ServiceTesting;

import com.summer_practice.demo.entities.Monitor;
import com.summer_practice.demo.entities.WorkPlace;
import com.summer_practice.demo.repositories.MonitorRepository;
import com.summer_practice.demo.repositories.WorkPlaceRepository;
import com.summer_practice.demo.services.MonitorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class TestMonitorService {
    @InjectMocks
    MonitorService monitorService;
    @Mock
    WorkPlaceRepository workPlaceRepository;
    @Mock
    MonitorRepository monitorRepository;

    @Test
    public void shouldAddMonitorSuccessfully() {
        WorkPlace workPLace = new WorkPlace();
        Timestamp data = Timestamp.valueOf("2021-06-18 13:54:33.964567");
        workPLace.setCreatedAt(data);
        workPLace.setCreatedBy("Daryna");
        workPLace.setUpdatedAt(Timestamp.valueOf("2021-06-18 13:54:33.964567"));
        workPLace.setUpdatedBy("Daryna");
        workPLace.setName("Working_Place_211");
        workPLace.setCity("Chernihiv");

        given(workPlaceRepository.findById(1L)).willReturn(Optional.of(workPLace));

        Monitor monitorTest = new Monitor();
        monitorTest.setCreatedBy("Asus");
        monitorTest.setUpdatedBy("Ihor");
        monitorTest.setwPlace(workPLace);
        monitorTest.setLength(242);
        monitorTest.setHeight(466);
        monitorTest.setWidth(622);
        monitorTest.setVesa("75x75mm");
        monitorTest.setDisplaySize("27" + "''");

        monitorService.addMonitor("Asus", "Ihor", 242, 466, 622, "75x75mm", "27''", 1L);
        verify(workPlaceRepository, times(2)).findById(1L);
        verify(monitorRepository, times(1)).save(any(Monitor.class));
    }

    @Test
    public void shouldUpdateMonitor() {
        WorkPlace workPLace = new WorkPlace();
        Timestamp data = Timestamp.valueOf("2021-06-18 13:54:33.964567");
        workPLace.setCreatedAt(data);
        workPLace.setCreatedBy("Daryna");
        workPLace.setUpdatedAt(Timestamp.valueOf("2021-06-18 13:54:33.964567"));
        workPLace.setUpdatedBy("Daryna");
        workPLace.setName("Working_Place_211");
        workPLace.setCity("Chernihiv");

        given(workPlaceRepository.findById(1L)).willReturn(Optional.of(workPLace));

        Monitor monitorTest = new Monitor();
        monitorTest.setCreatedBy("Asus");
        monitorTest.setUpdatedBy("Ihor");
        monitorTest.setwPlace(workPLace);
        monitorTest.setLength(242);
        monitorTest.setHeight(466);
        monitorTest.setWidth(622);
        monitorTest.setVesa("75x75mm");
        monitorTest.setDisplaySize("27" + "''");

        given(monitorRepository.findById(1L)).willReturn(Optional.of(monitorTest));
        monitorService.updateMonitor(1L, "Asus", "Ihor", 275, 466, 622, "75x75mm", "27''", 1L);
        verify(workPlaceRepository, times(2)).findById(1L);
        verify(monitorRepository, times(1)).save(monitorTest);
    }

    @Test
    public void shouldBeDeleted() {
        Monitor monitorTest = new Monitor();
        monitorTest.setCreatedBy("Asus");
        monitorTest.setUpdatedBy("Ihor");
        monitorTest.setwPlace(new WorkPlace());
        monitorTest.setLength(242);
        monitorTest.setHeight(466);
        monitorTest.setWidth(622);
        monitorTest.setVesa("75x75mm");
        monitorTest.setDisplaySize("27" + "''");
        given(monitorRepository.findById(1L)).willReturn(Optional.of(monitorTest));
        monitorService.deleteMonitor(1L);
        verify(monitorRepository, times(1)).deleteById(1L);
    }

    @Test
    public void shouldBeFoundByHeightBetween() {
        List<Monitor> monitors = new ArrayList<>();
        Monitor monitorTest = new Monitor();
        monitorTest.setCreatedBy("Asus");
        monitorTest.setUpdatedBy("Ihor");
        monitorTest.setwPlace(new WorkPlace());
        monitorTest.setLength(242);
        monitorTest.setHeight(466);
        monitorTest.setWidth(622);
        monitorTest.setVesa("75x75mm");
        monitorTest.setDisplaySize("27" + "''");
        monitors.add(monitorTest);
        given(monitorRepository.findByHeightBetween(400, 700)).willReturn(monitors);
        monitorService.findbyHeightsBetween(400, 700);
        verify(monitorRepository, times(1)).findByHeightBetween(400, 700);
    }

    @Test
    public void shouldBeFoundByVesa() {
        List<Monitor> monitors = new ArrayList<>();
        Monitor monitorTest = new Monitor();
        monitorTest.setCreatedBy("Asus");
        monitorTest.setUpdatedBy("Ihor");
        monitorTest.setwPlace(new WorkPlace());
        monitorTest.setLength(242);
        monitorTest.setHeight(466);
        monitorTest.setWidth(622);
        monitorTest.setVesa("75x75mm");
        monitorTest.setDisplaySize("27" + "''");
        monitors.add(monitorTest);
        given(monitorRepository.findByVesaContains("75")).willReturn(monitors);
        monitorService.findByVesa("75");
        verify(monitorRepository, times(1)).findByVesaContains("75");
    }
}
