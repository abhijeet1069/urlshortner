package com.backend.urlshortner.controller;

import com.backend.urlshortner.service.ShortUrlService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class RedirectController {

    private final ShortUrlService service;

    public RedirectController(ShortUrlService service) {
        this.service = service;
    }

    @GetMapping("/{code}")
    public String redirect(@PathVariable String code) {

        return service.findByCode(code)
                .map(u -> {
                    String url = u.originalUrl();
                    // ensure http:// or https://
                    if (!url.startsWith("http")) {
                        url = "https://" + url;
                    }
                    return "redirect:" + url;
                })
                .orElse("redirect:/notfound");
    }
}
