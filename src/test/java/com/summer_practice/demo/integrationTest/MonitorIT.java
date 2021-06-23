package com.summer_practice.demo.integrationTest;

import com.summer_practice.demo.entities.Monitor;
import com.summer_practice.demo.entities.WorkPlace;
import io.restassured.RestAssured;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@TestPropertySource(
    properties = {
      "spring.main.allow-bean-definition-overriding=true",
      "server.servlet.context-path=/"
    })
public class MonitorIT {

  @LocalServerPort private int port;

  @BeforeEach
  public void setUp() {
    RestAssured.port = port;
  }

  @Test
  public void givenURI_whenSendingReq_thenVerifyResponse() {

    given().get("/monitors").body().prettyPrint();
    given().get("/monitors").then().statusCode(200);
  }

  @Test
  public void ShouldPostMonitor() {
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
    monitorTest.setCreatedBy("Acer");
    monitorTest.setUpdatedBy("Mika");
    monitorTest.setwPlace(workPLace);
    monitorTest.setLength(300);
    monitorTest.setHeight(600);
    monitorTest.setWidth(822);
    monitorTest.setVesa("178x178mm");
    monitorTest.setDisplaySize("63''");

    RestTemplate restTemplate = new RestTemplate();
    HttpEntity<Monitor> requestBody = new HttpEntity<>(monitorTest);

    ResponseEntity<Monitor> responseEntity =
        restTemplate.postForEntity("http://localhost:8080/monitor", requestBody, Monitor.class);

    assertSame(responseEntity.getStatusCode(), HttpStatus.CREATED);
    assertThat(Objects.requireNonNull(responseEntity.getBody()).getMonId()).isNotNull();
    assertEquals(600, responseEntity.getBody().getHeight());
  }

  @Test
  public void ShouldPutMonitor() {
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
    monitorTest.setMonId(31L);
    monitorTest.setCreatedBy("Acer");
    monitorTest.setUpdatedBy("Mika");
    monitorTest.setwPlace(workPLace);
    monitorTest.setLength(400);
    monitorTest.setHeight(600);
    monitorTest.setWidth(822);
    monitorTest.setVesa("178x178mm");
    monitorTest.setDisplaySize("63''");

    RestTemplate restTemplate = new RestTemplate();
    HttpHeaders headers = new HttpHeaders();
    headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
    HttpEntity<Monitor> requestBody = new HttpEntity<>(monitorTest, headers);
    restTemplate.put("http://localhost:8080/monitor", requestBody);
    Monitor responseEntity =
        restTemplate.getForObject(
            "http://localhost:8080/monitorbyid/" + monitorTest.getMonId(), Monitor.class);

    assertEquals(600, responseEntity.getHeight());
  }

  @Test
  public void ShouldDeleteMonitor() {
    RestTemplate restTemplate = new RestTemplate();

    Map<String, String> params = new HashMap<>();
    params.put("id", "31");
    restTemplate.delete("http://localhost:8080/monitor/{id}", params);
    Monitor responseEntity =
        restTemplate.getForObject("http://localhost:8080/monitorbyid/31", Monitor.class);

    assertThat(responseEntity).isNull();
  }
}
