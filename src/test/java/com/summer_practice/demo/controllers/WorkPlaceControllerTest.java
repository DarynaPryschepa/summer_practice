package com.summer_practice.demo.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.summer_practice.demo.entities.WorkPlace;
import com.summer_practice.demo.services.WorkPlaceService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(WorkPlaceController.class)
@AutoConfigureMockMvc(addFilters = false)
public class WorkPlaceControllerTest {
  @Autowired private MockMvc mockMvc;

  @MockBean private WorkPlaceService workPlaceService;
  private ObjectMapper objectMapper = new ObjectMapper();

  @Test
  public void shouldAddWorkPLace() throws Exception {
    WorkPlace workPlace = new WorkPlace();
    workPlace.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
    workPlace.setCreatedBy("Nikita");
    workPlace.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
    workPlace.setUpdatedBy("Nikita");
    workPlace.setName("Working_Place_321");
    workPlace.setCity("Chernihiv");

    String workPlaceToJson = objectMapper.writeValueAsString(workPlace);

    given(workPlaceService.addWorkPlace(any(WorkPlace.class))).willReturn(workPlace);

    MvcResult mvcResult =
        this.mockMvc
            .perform(
                post("/workplace/")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(workPlaceToJson)
                    .characterEncoding("utf-8"))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.name").value("Working_Place_321"))
            .andDo(print())
            .andReturn();

    Assert.assertEquals("application/json", mvcResult.getResponse().getContentType());
    verify(workPlaceService, times(1)).addWorkPlace(any(WorkPlace.class));
  }

  @Test
  public void shouldUpdateWorkPLace() throws Exception {
    WorkPlace workPlace = new WorkPlace();
    workPlace.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
    workPlace.setCreatedBy("Nikita");
    workPlace.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
    workPlace.setUpdatedBy("N");
    workPlace.setName("Working_Place_321");
    workPlace.setCity("Chernihiv");

    String workPlaceToJson = objectMapper.writeValueAsString(workPlace);

    given(workPlaceService.updateWorkPlace(any(WorkPlace.class))).willReturn(workPlace);

    MvcResult mvcResult =
        this.mockMvc
            .perform(
                MockMvcRequestBuilders.put("/workplace")
                    .content(workPlaceToJson)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .characterEncoding("utf-8"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value("Working_Place_321"))
            .andDo(print())
            .andReturn();

    verify(workPlaceService, times(1)).updateWorkPlace(any(WorkPlace.class));

    Assert.assertEquals("application/json", mvcResult.getResponse().getContentType());
  }

  @Test
  public void shouldDeleteWorkPlace() throws Exception {
    given(workPlaceService.deleteWorkPlace(1L)).willReturn(true);
    mockMvc
        .perform(
            delete("/workplace/1/")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").value("true"))
        .andDo(print())
        .andReturn();

    verify(workPlaceService, times(1)).deleteWorkPlace(1L);
  }

  @Test
  public void shouldFindByName() throws Exception {
    List<WorkPlace> wplaceList = new ArrayList<>();
    WorkPlace workPlace = new WorkPlace();
    workPlace.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
    workPlace.setCreatedBy("Nikita");
    workPlace.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
    workPlace.setUpdatedBy("Nikita");
    workPlace.setName("Working_Place_321");
    workPlace.setCity("Chernihiv");
    wplaceList.add(workPlace);
    given(workPlaceService.findAllWorkingPlacesbyName("W")).willReturn(wplaceList);

    MvcResult mvcResult =
        this.mockMvc
            .perform(get("/workplacesbyname/W/"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].name").value("Working_Place_321"))
            .andReturn();

    Assert.assertEquals("application/json", mvcResult.getResponse().getContentType());
    verify(workPlaceService, times(1)).findAllWorkingPlacesbyName("W");
  }

  @Test
  public void shouldFindByCity() throws Exception {
    List<WorkPlace> wplaceList = new ArrayList<>();
    WorkPlace workPlace = new WorkPlace();
    workPlace.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
    workPlace.setCreatedBy("Nikita");
    workPlace.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
    workPlace.setUpdatedBy("Nikita");
    workPlace.setName("Working_Place_321");
    workPlace.setCity("Chernihiv");
    wplaceList.add(workPlace);
    given(workPlaceService.findAllWorkingPlacesbyCity("Chernihiv")).willReturn(wplaceList);

    MvcResult mvcResult =
        this.mockMvc
            .perform(get("/workplacesbycity/Chernihiv/"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].name").value("Working_Place_321"))
            .andReturn();

    Assert.assertEquals("application/json", mvcResult.getResponse().getContentType());
    verify(workPlaceService, times(1)).findAllWorkingPlacesbyCity("Chernihiv");
  }
}
