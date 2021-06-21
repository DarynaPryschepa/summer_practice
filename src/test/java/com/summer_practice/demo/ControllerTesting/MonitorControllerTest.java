package com.summer_practice.demo.ControllerTesting;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.summer_practice.demo.controllers.MonitorController;
import com.summer_practice.demo.entities.Monitor;
import com.summer_practice.demo.entities.WorkPlace;
import com.summer_practice.demo.services.MonitorService;
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

@WebMvcTest(MonitorController.class)
@AutoConfigureMockMvc(addFilters = false)
public class MonitorControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MonitorService monitorService;
    @MockBean
    private WorkPlaceService workPlaceService;

    @Test
    public void shouldAddMonitor() throws Exception {
        WorkPlace workPLace = new WorkPlace();
        Timestamp data = Timestamp.valueOf("2021-06-18 13:54:33.964567");
        workPLace.setCreatedAt(data);
        workPLace.setCreatedBy("Daryna");
        workPLace.setUpdatedAt(Timestamp.valueOf("2021-06-18 13:54:33.964567"));
        workPLace.setUpdatedBy("Daryna");
        workPLace.setName("Working_Place_211");
        workPLace.setCity("Chernihiv");
        workPLace.setId(1L);

        Monitor monitorTest = new Monitor();
        monitorTest.setCreatedBy("Asus");
        monitorTest.setUpdatedBy("Ihor");
        monitorTest.setwPlace(workPLace);
        monitorTest.setLength(242);
        monitorTest.setHeight(466);
        monitorTest.setWidth(622);
        monitorTest.setVesa("75x75mm");
        monitorTest.setDisplaySize("27" + "''");


        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = objectMapper.writer().withDefaultPrettyPrinter();
        String monitorToJson = ow.writeValueAsString(monitorTest);

        given(monitorService.addMonitor(any(Monitor.class))).willReturn(monitorTest);
        given(workPlaceService.findWorkingPlaceById(1L)).willReturn(workPLace);
        MvcResult mvcResult = this.mockMvc.perform(post("/workplaces/1/monitors")
                .contentType(MediaType.APPLICATION_JSON)
                .content(monitorToJson)
                .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vesa").value("75x75mm"))
                .andDo(print())
                .andReturn();

        Assert.assertEquals("application/json",
                mvcResult.getResponse().getContentType());
        verify(monitorService, times(1)).addMonitor(any(Monitor.class));
        verify(workPlaceService, times(1)).findWorkingPlaceById(1L);
    }

    @Test
    public void shouldUpdateMonitor() throws Exception {
        WorkPlace workPlace = new WorkPlace();
        workPlace.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        workPlace.setCreatedBy("Nikita");
        workPlace.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        workPlace.setUpdatedBy("Nikita");
        workPlace.setName("Working_Place_321");
        workPlace.setCity("Chernihiv");
        workPlace.setId(1L);

        Monitor monitorTest = new Monitor();
        monitorTest.setCreatedBy("Asus");
        monitorTest.setUpdatedBy("Ihor");
        monitorTest.setwPlace(workPlace);
        monitorTest.setLength(242);
        monitorTest.setHeight(466);
        monitorTest.setWidth(622);
        monitorTest.setVesa("75x75mm");
        monitorTest.setDisplaySize("27" + "''");


        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = objectMapper.writer().withDefaultPrettyPrinter();
        String monitorToJson = ow.writeValueAsString(monitorTest);

        given(workPlaceService.findWorkingPlaceById(1L)).willReturn(workPlace);
        given(monitorService.findMonitorById(1L)).willReturn(monitorTest);
        given(monitorService.updateMonitor(any(Monitor.class))).willReturn(monitorTest);

        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.put("/workplaces/1/monitors/1/")
                .content(monitorToJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.height").value(466))
                .andDo(print())
                .andReturn();

        verify(workPlaceService, times(1)).findWorkingPlaceById(1L);
        verify(monitorService, times(1)).findMonitorById(1L);
        verify(monitorService, times(1)).updateMonitor(any(Monitor.class));
        verify(monitorService, times(0)).addMonitor(any(Monitor.class));

        Assert.assertEquals("application/json",
                mvcResult.getResponse().getContentType());
    }

    @Test
    public void shouldDeleteMonitor() throws Exception {
        given(monitorService.deleteMonitor(1L)).willReturn(true);
        mockMvc.perform(delete("/monitors/1/")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("true"))
                .andDo(print())
                .andReturn();

        verify(monitorService, times(1)).deleteMonitor(1L);
    }

    @Test
    public void shouldFindByVesa() throws Exception {
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

        given(monitorService.findByVesa("75")).willReturn(monitors);

        MvcResult mvcResult = this.mockMvc.perform(get("/monitorsbyvesa/75"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].height").value(466))
                .andReturn();

        Assert.assertEquals("application/json",
                mvcResult.getResponse().getContentType());
        verify(monitorService, times(1)).findByVesa("75");

    }

    @Test
    public void shouldFindByHeight() throws Exception {
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

        given(monitorService.findbyHeightsBetween(200, 400)).willReturn(monitors);

        MvcResult mvcResult = this.mockMvc.perform(get("/monitorsbyh/200/400"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].height").value(466))
                .andReturn();

        Assert.assertEquals("application/json",
                mvcResult.getResponse().getContentType());
        verify(monitorService, times(1)).findbyHeightsBetween(200, 400);
    }
}