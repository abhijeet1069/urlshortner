package com.backend.urlshortner.controller;

import com.backend.urlshortner.model.ShortUrl;
import com.backend.urlshortner.repository.UrlRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/shortlink")
public class ShortUrlController {

    private final UrlRepository urlRepository;

    public ShortUrlController(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    @GetMapping
    public List<ShortUrl> getNotes() {
        return urlRepository.findAll();
    }
}
