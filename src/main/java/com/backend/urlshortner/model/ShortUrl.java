package com.backend.urlshortner.model;

public record ShortUrl(Long id, String shortCode, String originalUrl) {
}
