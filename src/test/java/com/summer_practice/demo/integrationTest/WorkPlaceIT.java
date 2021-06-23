package com.summer_practice.demo.integrationTest;

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
public class WorkPlaceIT {

  @LocalServerPort private int port;

  @BeforeEach
  public void setUp() {
    RestAssured.port = port;
  }

  @Test
  public void givenURI_whenSendingReq_thenVerifyResponse() {

    given().get("/workplaces").body().prettyPrint();
    given().get("/workplaces").then().statusCode(200);
  }

  @Test
  public void ShouldPostPC() {
    WorkPlace workPLace = new WorkPlace();
    Timestamp data = Timestamp.valueOf("2021-06-18 13:54:33.964567");
    workPLace.setCreatedAt(data);
    workPLace.setCreatedBy("Jack");
    workPLace.setUpdatedAt(Timestamp.valueOf("2021-06-18 13:54:33.964567"));
    workPLace.setUpdatedBy("Jack");
    workPLace.setName("Working_Place_311");
    workPLace.setCity("Chernihiv");

    RestTemplate restTemplate = new RestTemplate();
    HttpEntity<WorkPlace> requestBody = new HttpEntity<>(workPLace);

    ResponseEntity<WorkPlace> responseEntity =
        restTemplate.postForEntity("http://localhost:8080/workplace", requestBody, WorkPlace.class);

    assertSame(responseEntity.getStatusCode(), HttpStatus.CREATED);
    assertThat(Objects.requireNonNull(responseEntity.getBody()).getWplaceId()).isNotNull();
    assertEquals("Working_Place_311", responseEntity.getBody().getName());
  }

  @Test
  public void ShouldPutMonitor() {
    WorkPlace workPLace = new WorkPlace();
    Timestamp data = Timestamp.valueOf("2021-06-18 13:54:33.964567");
    workPLace.setCreatedAt(data);
    workPLace.setCreatedBy("Jack");
    workPLace.setUpdatedAt(Timestamp.valueOf("2021-06-18 13:54:33.964567"));
    workPLace.setUpdatedBy("Jasper");
    workPLace.setName("Working_Place_311");
    workPLace.setCity("Chernihiv");
    workPLace.setWplaceId(38L);

    RestTemplate restTemplate = new RestTemplate();
    HttpHeaders headers = new HttpHeaders();
    headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
    HttpEntity<WorkPlace> requestBody = new HttpEntity<>(workPLace, headers);
    restTemplate.put("http://localhost:8080/workplace", requestBody);
    WorkPlace responseEntity =
        restTemplate.getForObject(
            "http://localhost:8080/workplacebyid/" + workPLace.getWplaceId(), WorkPlace.class);

    assert responseEntity != null;
    assertEquals("Jasper", responseEntity.getName());
  }

  @Test
  public void ShouldDeleteMonitor() {
    RestTemplate restTemplate = new RestTemplate();

    Map<String, String> params = new HashMap<>();
    params.put("id", "38");
    restTemplate.delete("http://localhost:8080/workplace/{id}", params);
    WorkPlace responseEntity =
        restTemplate.getForObject("http://localhost:8080/workplacebyid/38", WorkPlace.class);

    assertThat(responseEntity).isNull();
  }
}
