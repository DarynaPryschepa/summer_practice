package com.summer_practice.demo.ServiceTesting;

import com.summer_practice.demo.entities.PC;
import com.summer_practice.demo.entities.WorkPlace;
import com.summer_practice.demo.repositories.PCRepository;
import com.summer_practice.demo.repositories.WorkPlaceRepository;
import com.summer_practice.demo.services.PCService;
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
public class TestPCService {
    @InjectMocks
    PCService pcService;
    @Mock
    WorkPlaceRepository workPlaceRepository;
    @Mock
    PCRepository pcRepository;

    @Test
    public void shouldAddPCSuccessfully() {
        WorkPlace workPLace = new WorkPlace();
        Timestamp data = Timestamp.valueOf("2021-06-18 13:54:33.964567");
        workPLace.setCreatedAt(data);
        workPLace.setCreatedBy("Daryna");
        workPLace.setUpdatedAt(Timestamp.valueOf("2021-06-18 13:54:33.964567"));
        workPLace.setUpdatedBy("Daryna");
        workPLace.setName("Working_Place_211");
        workPLace.setCity("Chernihiv");

        given(workPlaceRepository.findById(1L)).willReturn(Optional.of(workPLace));

        PC pcTest = new PC();
        pcTest.setCreatedAt(Timestamp.valueOf("2021-06-18 12:37:49.088739"));
        pcTest.setCreatedBy("Asus");
        pcTest.setUpdatedAt(Timestamp.valueOf("2021-06-18 12:37:49.088859"));
        pcTest.setUpdatedBy("Ihor");

        pcTest.setLength(242);
        pcTest.setHeight(466);
        pcTest.setWidth(622);
        pcTest.setCpuCount(4);
        pcTest.setHddSize(400);

        pcService.addPC("Asus", "Ihor", 242, 466, 622, 4, 400, 1L);
        verify(workPlaceRepository, times(2)).findById(1L);
        verify(pcRepository, times(1)).save(any(PC.class));
    }

    @Test
    public void shouldUpdatePC() {
        WorkPlace workPLace = new WorkPlace();
        Timestamp data = Timestamp.valueOf("2021-06-18 13:54:33.964567");
        workPLace.setCreatedAt(data);
        workPLace.setCreatedBy("Daryna");
        workPLace.setUpdatedAt(Timestamp.valueOf("2021-06-18 13:54:33.964567"));
        workPLace.setUpdatedBy("Daryna");
        workPLace.setName("Working_Place_211");
        workPLace.setCity("Chernihiv");

        given(workPlaceRepository.findById(1L)).willReturn(Optional.of(workPLace));

        PC pcTest = new PC();
        pcTest.setCreatedAt(Timestamp.valueOf("2021-06-18 12:37:49.088739"));
        pcTest.setCreatedBy("Asus");
        pcTest.setUpdatedAt(Timestamp.valueOf("2021-06-18 12:37:49.088859"));
        pcTest.setUpdatedBy("Ihor");

        pcTest.setLength(242);
        pcTest.setHeight(466);
        pcTest.setWidth(622);
        pcTest.setCpuCount(4);
        pcTest.setHddSize(400);

        given(pcRepository.findById(1L)).willReturn(Optional.of(pcTest));
        pcService.updatePC(1L, "Asus", "Ihor", 242, 466, 622, 4, 400, 1L);
        verify(workPlaceRepository, times(2)).findById(1L);
        verify(pcRepository, times(1)).save(pcTest);
    }

    @Test
    public void shouldBeDeleted() {
        PC pcTest = new PC();
        pcTest.setCreatedAt(Timestamp.valueOf("2021-06-18 12:37:49.088739"));
        pcTest.setCreatedBy("Asus");
        pcTest.setUpdatedAt(Timestamp.valueOf("2021-06-18 12:37:49.088859"));
        pcTest.setUpdatedBy("Ihor");

        pcTest.setLength(242);
        pcTest.setHeight(466);
        pcTest.setWidth(622);
        pcTest.setCpuCount(4);
        pcTest.setHddSize(400);

        given(pcRepository.findById(1L)).willReturn(Optional.of(pcTest));
        pcService.deletePC(1L);
        verify(pcRepository, times(1)).deleteById(1L);
    }

    @Test
    public void shouldBeFoundByCPU() {
        List<PC> pcs = new ArrayList<>();

        PC pcTest = new PC();
        pcTest.setCreatedAt(Timestamp.valueOf("2021-06-18 12:37:49.088739"));
        pcTest.setCreatedBy("Asus");
        pcTest.setUpdatedAt(Timestamp.valueOf("2021-06-18 12:37:49.088859"));
        pcTest.setUpdatedBy("Ihor");

        pcTest.setLength(242);
        pcTest.setHeight(466);
        pcTest.setWidth(622);
        pcTest.setCpuCount(4);
        pcTest.setHddSize(400);
        pcs.add(pcTest);
        given(pcRepository.findByCpuCount(4)).willReturn(pcs);
        pcService.findAllByCpuCount(4);
        verify(pcRepository, times(1)).findByCpuCount(4);
    }

    @Test
    public void shouldBeFoundByHddSize() {
        List<PC> pcs = new ArrayList<>();

        PC pcTest = new PC();
        pcTest.setCreatedAt(Timestamp.valueOf("2021-06-18 12:37:49.088739"));
        pcTest.setCreatedBy("Asus");
        pcTest.setUpdatedAt(Timestamp.valueOf("2021-06-18 12:37:49.088859"));
        pcTest.setUpdatedBy("Ihor");

        pcTest.setLength(242);
        pcTest.setHeight(466);
        pcTest.setWidth(622);
        pcTest.setCpuCount(4);
        pcTest.setHddSize(400);
        pcs.add(pcTest);

        given(pcRepository.findByHddSize(300)).willReturn(pcs);
        pcService.findAllByHddSize(300);
        verify(pcRepository, times(1)).findByHddSize(300);
    }
}
