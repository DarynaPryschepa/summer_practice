package com.summer_practice.demo.integrationTest;

import com.summer_practice.demo.entities.PC;
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
public class PcIT {

  @LocalServerPort private int port;

  @BeforeEach
  public void setUp() {
    RestAssured.port = port;
  }

  @Test
  public void givenURI_whenSendingReq_thenVerifyResponse() {

    given().get("/pcs").body().prettyPrint();
    given().get("/pcs").then().statusCode(200);
  }

  @Test
  public void ShouldPostPC() {
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
    pcTest.setCreatedAt(Timestamp.valueOf("2021-06-23 12:37:49.088739"));
    pcTest.setCreatedBy("Acer");
    pcTest.setUpdatedAt(Timestamp.valueOf("2021-06-23 12:37:49.088859"));
    pcTest.setUpdatedBy("Mika");

    pcTest.setLength(322);
    pcTest.setHeight(789);
    pcTest.setWidth(900);
    pcTest.setCpuCount(6);
    pcTest.setHddSize(800);
    pcTest.setwPlacePc(workPLace);

    RestTemplate restTemplate = new RestTemplate();
    HttpEntity<PC> requestBody = new HttpEntity<>(pcTest);

    ResponseEntity<PC> responseEntity =
        restTemplate.postForEntity("http://localhost:8080/pc", requestBody, PC.class);

    assertSame(responseEntity.getStatusCode(), HttpStatus.CREATED);
    assertThat(Objects.requireNonNull(responseEntity.getBody()).getPcId()).isNotNull();
    assertEquals(789, responseEntity.getBody().getHeight());
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

    PC pcTest = new PC();
    pcTest.setCreatedAt(Timestamp.valueOf("2021-06-23 12:37:49.088739"));
    pcTest.setCreatedBy("Acer");
    pcTest.setUpdatedAt(Timestamp.valueOf("2021-06-23 12:37:49.088859"));
    pcTest.setUpdatedBy("Iren");

    pcTest.setLength(322);
    pcTest.setHeight(789);
    pcTest.setWidth(900);
    pcTest.setCpuCount(6);
    pcTest.setHddSize(800);
    pcTest.setPcId(35L);
    pcTest.setwPlacePc(workPLace);

    RestTemplate restTemplate = new RestTemplate();
    HttpHeaders headers = new HttpHeaders();
    headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
    HttpEntity<PC> requestBody = new HttpEntity<>(pcTest, headers);
    restTemplate.put("http://localhost:8080/pc", requestBody);
    PC responseEntity =
        restTemplate.getForObject("http://localhost:8080/pcbyid/" + pcTest.getPcId(), PC.class);

    assertEquals(789, responseEntity.getHeight());
  }

  @Test
  public void ShouldDeleteMonitor() {
    RestTemplate restTemplate = new RestTemplate();

    Map<String, String> params = new HashMap<>();
    params.put("id", "36");
    restTemplate.delete("http://localhost:8080/pc/{id}", params);
    PC responseEntity = restTemplate.getForObject("http://localhost:8080/pcbyid/36", PC.class);

    assertThat(responseEntity).isNull();
  }
}
