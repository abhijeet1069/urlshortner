package com.backend.urlshortner.service;

import com.backend.urlshortner.controller.ShortUrlApiController;
import com.backend.urlshortner.model.ShortUrl;
import com.backend.urlshortner.repository.UrlRepository;
import com.backend.urlshortner.util.Base62;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShortUrlService {
    private final UrlRepository repo;
    private static final Logger log = LoggerFactory.getLogger(ShortUrlService.class);
    public ShortUrlService(UrlRepository repo) {
        this.repo = repo;
    }

    public String createShortUrl(String originalUrl){
        long id = repo.insertUrl(originalUrl);
        String code = Base62.encode(id);
        repo.updateShortCode(id,code);
        return code;
    }

    public Optional<ShortUrl> findByCode(String code) {
        return repo.findByShortCode(code);
    }

    public Optional<List<ShortUrl>> findAll(){
        log.info("findByCode() invoked");
        return Optional.ofNullable(repo.findAll());
    }
}
