package com.autoflex.resource;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.ws.rs.core.MediaType;

@QuarkusTest
class RawMaterialResourceTest {

    @Test
    void shouldListRawMaterials() {
        given()
            .when()
            .get("/raw-materials")
            .then()
            .statusCode(200);
    }

    @Test
    void shouldCreateRawMaterial() {
        given()
            .contentType(MediaType.APPLICATION_JSON)
            .body("""
                {
                  "name": "Steel",
                  "stockQuantity": 100
                }
            """)
            .when()
            .post("/raw-materials")
            .then()
            .statusCode(200)
            .body("id", notNullValue())
            .body("name", is("Steel"))
            .body("stockQuantity", is(100));
    }

}

