package com.backend.urlshortner.core.repository;

import com.backend.urlshortner.core.model.ShortUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends JpaRepository<ShortUrl,Long> {

}
