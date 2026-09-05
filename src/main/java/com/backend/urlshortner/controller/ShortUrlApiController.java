package com.backend.urlshortner.controller;

import com.backend.urlshortner.model.ShortUrl;
import com.backend.urlshortner.service.ShortUrlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ShortUrlApiController {

    private static final Logger log = LoggerFactory.getLogger(ShortUrlApiController.class);
    private final ShortUrlService service;
    private final String baseUrl;

    public ShortUrlApiController(ShortUrlService service,
                                 @Value("${app.base-url}") String baseUrl) {
        this.service = service;
        this.baseUrl = baseUrl;
    }

    @PostMapping("/shorten")
    public Map<String, String> shorten(@RequestBody ShortenRequest request) {
        log.info("Creating short URL");
        String code = service.createShortUrl(request.url());
        return Map.of("shortUrl", baseUrl + "/" + code);
    }

    @GetMapping("/all")
    public List<ShortUrl> getAllUrls() {
        return service.findAll();
    }
}
