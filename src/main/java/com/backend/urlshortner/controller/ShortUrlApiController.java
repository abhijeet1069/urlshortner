package com.backend.urlshortner.controller;

import com.backend.urlshortner.model.ShortUrl;
import com.backend.urlshortner.service.ShortUrlService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ShortUrlApiController {

    private final ShortUrlService service;

    public ShortUrlApiController(ShortUrlService service) {
        this.service = service;
    }

    @PostMapping("/shorten")
    public Map<String, String> shorten(@RequestBody Map<String, String> body) {
        String url = body.get("url");
        String code = service.createShortUrl(url);

        return Map.of(
                "shortUrl", "http://localhost:8080/" + code
        );
    }

    @GetMapping("/all")
    public List<ShortUrl> getNotes() {
        return service.findAll().orElseGet(ArrayList::new);
    }
}
