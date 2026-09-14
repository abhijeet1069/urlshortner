package com.backend.urlshortner.core.e2e;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class UrlShortenerE2ETest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void should_shorten_and_redirect() throws Exception {
        // 1. Create short URL
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
                        String.class);

        assertEquals(
                HttpStatus.OK,
                shortenResponse.getStatusCode());

        // 2. Extract short URL from response
        JsonNode json = objectMapper.readTree(
                        shortenResponse.getBody());

        String shortUrl = json.get("shortUrl").asText();

        assertNotNull(shortUrl);

        // 3. Extract short code
        String code = URI.create(shortUrl)
                        .getPath()
                        .substring(1);

        // 4. Resolve short URL
        ResponseEntity<String> redirectResponse = restTemplate.getForEntity(
                        "/" + code,
                        String.class);

        assertEquals(HttpStatus.OK,
                redirectResponse.getStatusCode());

        // 5. Verify redirect
        assertNotNull(redirectResponse.getBody());
        assertFalse(redirectResponse.getBody().isBlank());
    }
}