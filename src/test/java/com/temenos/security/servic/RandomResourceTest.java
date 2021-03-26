package com.temenos.security.servic;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.startsWith;

@QuarkusTest
public class RandomResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/random")
          .then()
             .statusCode(200)
             .body(startsWith("Hello RESTEasy"));
    }

    @Test
    public void testUserEndpoint() {
        given()
          .when().get("/user/me")
          .then()
             .statusCode(200)
             .body(startsWith("{\"userName\":\"hhhhh"));
    }   

}