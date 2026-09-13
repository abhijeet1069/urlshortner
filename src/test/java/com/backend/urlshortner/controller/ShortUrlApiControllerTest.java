package com.backend.urlshortner.controller;
import com.backend.urlshortner.model.ShortUrl;
import com.backend.urlshortner.service.ShortUrlService;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ShortUrlApiController.class)
class ShortUrlApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ShortUrlService service;

    @Test
    void should_shorten_url() throws Exception {
        when(service.createShortUrl("https://google.com"))
                .thenReturn("10");

        mockMvc.perform(
                        post("/api/shorten")
                                .contentType("application/json")
                                .content("""
                                {
                                    "url": "https://google.com"
                                }
                                """)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.shortUrl")
                        .value("http://localhost:8080/10"));
    }

    @Test
    void should_return_all_urls() throws Exception {
        ShortUrl url =
                new ShortUrl("https://google.com");

        when(service.findAll())
                .thenReturn(List.of(url));

        mockMvc.perform(get("/api/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].originalUrl")
                        .value("https://google.com"));
    }
}