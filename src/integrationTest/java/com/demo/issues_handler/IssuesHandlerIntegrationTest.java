package com.demo.issues_handler;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

class IssuesHandlerIntegrationTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(IssuesHandlerIntegrationTest.class);
    private static final String DEFAULT_BASE_URL = "http://localhost:8080";

    private static String baseUrl;

    @BeforeAll
    public static void setup() {
        if (System.getenv("BASE_URL") == null) {
            LOGGER.info("BASE_URL environment variable is not set. Using default: {}", DEFAULT_BASE_URL);
            baseUrl = DEFAULT_BASE_URL;
        } else {
            baseUrl = System.getenv("BASE_URL");
            LOGGER.info("Using base URL: {}", baseUrl);
        }
    }

    @Test
    void getIsssuesData_MultipleLabels_Success() {
        Map<String, Object> expected = Map.of(
                "total", 8,
                "labelCounts", Map.of(
                        "bug", 2,
                        "status/need-investigation", 1,
                        "status/invalid", 1,
                        "status/duplicate", 1,
                        "status/working-as-intended", 1,
                        "status/under-review", 1
                )
        );
        Map response = get(baseUrl + "/issuesdata/reactor/reactor-pool")
                .then()
                .statusCode(200)
                .extract()
                .as(Map.class);
        assertEquals(expected, response);
                //.body("total", hasSize(greaterThan(0)));
//                .body("total", equalTo(8))
//                .body("labelCounts.bug", equalTo(2))
//                .body("labelCounts", hasKey("status/need-investigation"));

    }

    @Test
    void getIssuesData_InvalidRepo_NotFound() {
        String url = baseUrl + "/issuesdata/abc/def";
        Response response = RestAssured.get(url);
        assertEquals(404, response.getStatusCode());
        //assertTrue(response.jsonPath().getList("issues").size() > 0);
    }

}
