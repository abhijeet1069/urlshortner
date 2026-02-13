package com.backend.urlshortner.model;

public class ShortUrl {
    private Long id;
    private String shortCode;
    private String originalUrl;
    private int clickCount;

    public ShortUrl(Long id, String shortCode, String originalUrl, int clickCount) {
        this.id = id;
        this.shortCode = shortCode;
        this.originalUrl = originalUrl;
        this.clickCount = clickCount;
    }

    public Long getId() {
        return id;
    }

    public String getShortCode() {
        return shortCode;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public int getClickCount() {
        return clickCount;
    }
}
