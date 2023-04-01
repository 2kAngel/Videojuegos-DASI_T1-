package es.upsa.dasi.resources;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class VideojuegosResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/games")
          .then()
             .statusCode(200)
             .body(is("Hello RESTEasy"));
    }

}