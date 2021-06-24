package com.summer_practice.demo.services;

import com.summer_practice.demo.entities.Monitor;
import com.summer_practice.demo.entities.WorkPlace;
import com.summer_practice.demo.repositories.MonitorRepository;
import com.summer_practice.demo.repositories.WorkPlaceRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class MonitorServiceTest {
  @InjectMocks MonitorService monitorService;

  @Mock MonitorRepository monitorRepository;
  @Mock WorkPlaceRepository workPlaceRepository;
  @Captor ArgumentCaptor<Monitor> monitorArgumentCaptor;

  @Test
  public void shouldAddMonitorSuccessfully() {
    WorkPlace workPLace = new WorkPlace();
    workPLace.setWplaceId(1L);
    Timestamp data = Timestamp.valueOf("2021-06-18 13:54:33.964567");
    workPLace.setCreatedAt(data);
    workPLace.setCreatedBy("Daryna");
    workPLace.setUpdatedAt(Timestamp.valueOf("2021-06-18 13:54:33.964567"));
    workPLace.setUpdatedBy("Daryna");
    workPLace.setName("Working_Place_211");
    workPLace.setCity("Chernihiv");

    Monitor monitorTest = new Monitor();
    monitorTest.setCreatedBy("Asus");
    monitorTest.setUpdatedBy("Ihor");
    monitorTest.setwPlace(workPLace);
    monitorTest.setLength(242);
    monitorTest.setHeight(466);
    monitorTest.setWidth(622);
    monitorTest.setVesa("75x75mm");
    monitorTest.setDisplaySize("27''");
    monitorTest.setMonId(1L);
    given(workPlaceRepository.getById(1L)).willReturn(workPLace);
    given(monitorRepository.save(monitorArgumentCaptor.capture())).willReturn(monitorTest);
    Monitor expected = monitorService.addMonitor(monitorTest);
    assertEquals(expected, monitorArgumentCaptor.getValue());
    verify(workPlaceRepository, times(1)).getById(1L);
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
    workPLace.setWplaceId(1L);
    Monitor monitorTest = new Monitor();
    monitorTest.setCreatedBy("Asus");
    monitorTest.setUpdatedBy("Ihor");
    monitorTest.setwPlace(workPLace);
    monitorTest.setLength(242);
    monitorTest.setHeight(466);
    monitorTest.setWidth(622);
    monitorTest.setVesa("75x75mm");
    monitorTest.setDisplaySize("27''");
    monitorTest.setMonId(1L);
    given(workPlaceRepository.getById(1L)).willReturn(workPLace);
    given(monitorRepository.getById(1L)).willReturn(monitorTest);
    given(monitorRepository.save(monitorArgumentCaptor.capture())).willReturn(monitorTest);
    Monitor expected = monitorService.updateMonitor(monitorTest);
    assertEquals(expected, monitorArgumentCaptor.getValue());
    verify(monitorRepository, times(1)).getById(1L);
    verify(workPlaceRepository, times(1)).getById(1L);
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
    monitorTest.setDisplaySize("27''");
    given(monitorRepository.findById(1L)).willReturn(Optional.of(monitorTest));
    boolean expected = monitorService.deleteMonitor(1L);
    assertTrue(expected);
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
    monitorTest.setDisplaySize("27''");
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
    monitorTest.setDisplaySize("27''");
    monitors.add(monitorTest);
    given(monitorRepository.findByVesaContains("75")).willReturn(monitors);
    monitorService.findByVesa("75");
    verify(monitorRepository, times(1)).findByVesaContains("75");
  }
}
