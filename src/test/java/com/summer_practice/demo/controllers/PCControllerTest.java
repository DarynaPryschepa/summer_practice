package com.summer_practice.demo.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.summer_practice.demo.entities.PC;
import com.summer_practice.demo.entities.WorkPlace;
import com.summer_practice.demo.services.PCService;
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
@WebMvcTest(PCController.class)
@AutoConfigureMockMvc(addFilters = false)
public class PCControllerTest {
  @Autowired private MockMvc mockMvc;

  @MockBean private PCService pcService;
 private  ObjectMapper objectMapper = new ObjectMapper();

  @Test
  public void shouldAddPC() throws Exception {
    WorkPlace workPLace = new WorkPlace();
    Timestamp data = Timestamp.valueOf("2021-06-18 13:54:33.964567");
    workPLace.setCreatedAt(data);
    workPLace.setCreatedBy("Daryna");
    workPLace.setUpdatedAt(Timestamp.valueOf("2021-06-18 13:54:33.964567"));
    workPLace.setUpdatedBy("Daryna");
    workPLace.setName("Working_Place_211");
    workPLace.setCity("Chernihiv");
    workPLace.setWplaceId(1L);

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

    String pcToJson = objectMapper.writeValueAsString(pcTest);

    given(pcService.addPC(any(PC.class))).willReturn(pcTest);
    MvcResult mvcResult =
        this.mockMvc
            .perform(
                post("/pc")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(pcToJson)
                    .characterEncoding("utf-8"))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.cpuCount").value("4"))
            .andDo(print())
            .andReturn();

    Assert.assertEquals("application/json", mvcResult.getResponse().getContentType());
    verify(pcService, times(1)).addPC(any(PC.class));
  }

  @Test
  public void shouldUpdatePC() throws Exception {
    WorkPlace workPlace = new WorkPlace();
    workPlace.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
    workPlace.setCreatedBy("Nikita");
    workPlace.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
    workPlace.setUpdatedBy("Nikita");
    workPlace.setName("Working_Place_321");
    workPlace.setCity("Chernihiv");
    workPlace.setWplaceId(1L);

    PC pcTest = new PC();
    pcTest.setCreatedAt(Timestamp.valueOf("2021-06-18 12:37:49.088739"));
    pcTest.setCreatedBy("Asus");
    pcTest.setUpdatedAt(Timestamp.valueOf("2021-06-18 12:37:49.088859"));
    pcTest.setUpdatedBy("Ihor");

    pcTest.setLength(242);
    pcTest.setHeight(466);
    pcTest.setWidth(622);
    pcTest.setCpuCount(6);
    pcTest.setHddSize(400);

    String pcToJson = objectMapper.writeValueAsString(pcTest);

    given(pcService.updatePC(any(PC.class))).willReturn(pcTest);

    MvcResult mvcResult =
        this.mockMvc
            .perform(
                MockMvcRequestBuilders.put("/pc")
                    .content(pcToJson)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .characterEncoding("utf-8"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.cpuCount").value(6))
            .andDo(print())
            .andReturn();

    verify(pcService, times(1)).updatePC(any(PC.class));

    Assert.assertEquals("application/json", mvcResult.getResponse().getContentType());
  }

  @Test
  public void shouldDeletePc() throws Exception {
    given(pcService.deletePC(1L)).willReturn(true);
    mockMvc
        .perform(
            delete("/pc/1/").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").value("true"))
        .andDo(print())
        .andReturn();

    verify(pcService, times(1)).deletePC(1L);
  }

  @Test
  public void shouldFindByCpu() throws Exception {
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

    given(pcService.findAllByCpuCount(4)).willReturn(pcs);

    MvcResult mvcResult =
        this.mockMvc
            .perform(get("/pcsbycpu/4/"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].cpuCount").value(4))
            .andReturn();

    Assert.assertEquals("application/json", mvcResult.getResponse().getContentType());
    verify(pcService, times(1)).findAllByCpuCount(4);
  }

  @Test
  public void shouldFindByHdd() throws Exception {
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

    given(pcService.findAllByHddSize(400)).willReturn(pcs);

    MvcResult mvcResult =
        this.mockMvc
            .perform(get("/pcsbyhdd/400/"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].hddSize").value(400))
            .andReturn();

    Assert.assertEquals("application/json", mvcResult.getResponse().getContentType());
    verify(pcService, times(1)).findAllByHddSize(400);
  }
}
