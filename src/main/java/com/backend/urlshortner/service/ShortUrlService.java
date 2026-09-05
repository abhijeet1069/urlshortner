package com.backend.urlshortner.service;

import com.backend.urlshortner.model.ShortUrl;
import com.backend.urlshortner.repository.UrlRepository;
import com.backend.urlshortner.util.Base62;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShortUrlService {

    private final UrlRepository repo;

    public ShortUrlService(UrlRepository repo) {
        this.repo = repo;
    }

    //save URL in DB and encode the id to shorten
    public String createShortUrl(String originalUrl){
        ShortUrl url = repo.save(new ShortUrl(originalUrl));
        return Base62.encode(url.getId());
    }

    //Decode the id and find that id in DB
    public Optional<ShortUrl> findByCode(String code) {
        long id = Base62.decode(code);
        return repo.findById(id);
    }

    public List<ShortUrl> findAll(){
        return repo.findAll();
    }
}
