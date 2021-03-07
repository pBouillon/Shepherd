package com.fisæ.shepherd.api.controller;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class ControllerTests {

    /**
     * Default port of the API
     */
    public static final int DEFAULT_PORT = 8080;

    /**
     * Default URL to access the API
     */
    public static final String DEFAULT_URL = "http://localhost";

    /**
     * Template used to perform integration tests
     */
    protected final RestTemplate restTemplate = new RestTemplate();

    /**
     * Port used to perform integration tests
     */
    @LocalServerPort
    protected int serverPort = DEFAULT_PORT;

    /**
     * Extract the payload of a response entity and ensure it exists
     *
     * @param responseEntity ResponseEntity from which extract the payload
     * @param <T> Type of the payload to extract
     *
     * @return The extracted payload
     */
    protected <T> T extractPayload(ResponseEntity<T> responseEntity) {
        T payload = responseEntity.getBody();

        assertNotNull(payload);

        return payload;
    }

    /**
     * Get the full URI for the provided route
     *
     * @param route API endpoint route
     *
     * @return The full URI for the route
     *
     * @throws URISyntaxException When the URI is invalid
     */
    protected URI getUriForRoute(String route) throws URISyntaxException {
        return new URI(DEFAULT_URL + ":" + serverPort + route);
    }

}
