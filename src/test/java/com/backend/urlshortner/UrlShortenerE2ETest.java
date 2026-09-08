package com.backend.urlshortner;

import com.backend.urlshortner.controller.ShortenRequest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class UrlShortenerE2ETest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldShortenAndRedirect() throws Exception {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(

                """
                {
                    "url": "https://google.com"
                }
                """,
                headers
        );

        ResponseEntity<String> shortenResponse =
                restTemplate.postForEntity(
                        "/api/shorten",
                        request,
                        String.class
                );

        JsonNode json =
                objectMapper.readTree(shortenResponse.getBody());

        String shortUrl =
                json.get("shortUrl").asText();

        // Extract /1 from http://localhost:8080/1
        String code =
                URI.create(shortUrl)
                        .getPath()
                        .substring(1);

        // Follow the short URL
        ResponseEntity<Void> redirectResponse =
                restTemplate.getForEntity(
                        "/" + code,
                        Void.class
                );

        // Verify redirect
        assertEquals(
                HttpStatus.OK,
                redirectResponse.getStatusCode()
        );

        assertEquals(
                URI.create("https://google.com"),
                redirectResponse.getHeaders().getLocation()
        );
    }
}