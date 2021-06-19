package com.summer_practice.demo.ServiceTesting;

import com.summer_practice.demo.entities.WorkPlace;
import com.summer_practice.demo.repositories.WorkPlaceRepository;
import com.summer_practice.demo.services.WorkPlaceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TestWorkPlaceService {
    @InjectMocks
    WorkPlaceService workPlaceService;
    @Mock
    WorkPlaceRepository workPlaceRepository;

    @Test
    public void shouldAddWorkPlaceSuccessfully() {
        final WorkPlace workPlace = new WorkPlace();
        Timestamp data = Timestamp.valueOf(LocalDateTime.now());
        workPlace.setCreatedAt(data);
        workPlace.setCreatedBy("Nikita");
        workPlace.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        workPlace.setUpdatedBy("Nikita");
        workPlace.setName("Working_Place_321");
        workPlace.setCity("Chernihiv");

        workPlaceService.addWorkPlace("Nikita", "Nikita", "Working_Place_321", "Chernihiv");
        verify(workPlaceRepository, times(1)).save(any(WorkPlace.class));
    }

    @Test
    public void shouldUpdateWorkPLace() {
        WorkPlace workPLace = new WorkPlace();
        Timestamp data = Timestamp.valueOf("2021-06-18 13:54:33.964567");
        workPLace.setCreatedAt(data);
        workPLace.setCreatedBy("Daryna");
        workPLace.setUpdatedAt(Timestamp.valueOf("2021-06-18 13:54:33.964567"));
        workPLace.setUpdatedBy("Daryna");
        workPLace.setName("Working_Place_211");
        workPLace.setCity("Chernihiv");

        given(workPlaceRepository.findById(1L)).willReturn(Optional.of(workPLace));
        workPlaceService.updateWorkPlace(1L, "Daryna", "Daryna", "Working_Place_211", "Kyiv");
        verify(workPlaceRepository, times(1)).save(workPLace);
    }

    @Test
    public void shouldBeDeleted() {
        WorkPlace workPLace = new WorkPlace();
        Timestamp data = Timestamp.valueOf("2021-06-18 13:54:33.964567");
        workPLace.setCreatedAt(data);
        workPLace.setCreatedBy("Daryna");
        workPLace.setUpdatedAt(Timestamp.valueOf("2021-06-18 13:54:33.964567"));
        workPLace.setUpdatedBy("Daryna");
        workPLace.setName("Working_Place_211");
        workPLace.setCity("Chernihiv");
        given(workPlaceRepository.findById(1L)).willReturn(Optional.of(workPLace));
        workPlaceService.deleteWorkPlace(1L);
        verify(workPlaceRepository, times(1)).deleteById(1L);
    }

    @Test
    public void shouldBeFoundByCity() {
        List<WorkPlace> wplaceList = new ArrayList<>();
        WorkPlace workPlace = new WorkPlace();
        workPlace.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        workPlace.setCreatedBy("Nikita");
        workPlace.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        workPlace.setUpdatedBy("Nikita");
        workPlace.setName("Working_Place_321");
        workPlace.setCity("Chernihiv");
        wplaceList.add(workPlace);
        given(workPlaceRepository.findByCity("Chernigiv")).willReturn(wplaceList);
        workPlaceService.findAllWorkingPlacesbyCity("Chernigiv");
        verify(workPlaceRepository, times(1)).findByCity("Chernigiv");
    }

    @Test
    public void shouldBeFoundByName() {
        List<WorkPlace> wplaceList = new ArrayList<>();
        WorkPlace workPlace = new WorkPlace();
        workPlace.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        workPlace.setCreatedBy("Nikita");
        workPlace.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        workPlace.setUpdatedBy("Nikita");
        workPlace.setName("Working_Place_321");
        workPlace.setCity("Chernihiv");
        wplaceList.add(workPlace);

        given(workPlaceRepository.findByName("W")).willReturn(wplaceList);
        workPlaceService.findAllWorkingPlacesbyName("W");
        verify(workPlaceRepository, times(1)).findByName("W");
    }
}


