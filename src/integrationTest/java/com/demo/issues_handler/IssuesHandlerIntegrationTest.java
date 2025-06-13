package com.demo.issues_handler;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static io.restassured.RestAssured.*;

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
                        "status/need-investigation", 2,
                        ":question:need-triage", 1,
                        "for/user-attention", 2,
                        "type/enhancement", 4
                )
        );
        Map response = get(baseUrl + "/issuesdata/reactor/reactor-pool")
                .then()
                .statusCode(200)
                .extract()
                .as(Map.class);
        assertEquals(expected, response);
    }

    @Test
    void getIssuesData_InvalidRepo_NotFound() {
        String url = baseUrl + "/issuesdata/abc/def";
        Response response = RestAssured.get(url);
        assertEquals(404, response.getStatusCode());
        //assertTrue(response.jsonPath().getList("issues").size() > 0);
    }

}
