package com.summer_practice.demo.integrationTest;

import io.restassured.RestAssured;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;

import static io.restassured.RestAssured.given;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@TestPropertySource(
    properties = {
      "spring.main.allow-bean-definition-overriding=true",
      "server.servlet.context-path=/"
    })
public class IntegrationTest {

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
}
