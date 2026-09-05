package com.backend.urlshortner.model;

import jakarta.persistence.*;

import javax.crypto.ShortBufferException;

@Entity
@Table(name="urls")
public class ShortUrl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="original_url",nullable = false)
    private String originalUrl;

    protected ShortUrl(){
        //required by JPA
    }

    public ShortUrl(String originalUrl){
        this.originalUrl = originalUrl;
    }

    public Long getId(){
        return id;
    }

    public String getOriginalUrl(){
        return originalUrl;
    }
}
