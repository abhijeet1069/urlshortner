package com.backend.urlshortner.service;

import com.backend.urlshortner.model.ShortUrl;
import com.backend.urlshortner.repository.UrlRepository;
import com.backend.urlshortner.util.Base62;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ShortUrlServiceTest {

    @Mock
    private UrlRepository repo;

    private ShortUrlService service;

    @BeforeEach
    void setup(){
        service = new ShortUrlService(repo);
    }

    @Test
    void should_find_url_by_code() {
        ShortUrl url = new ShortUrl("https://google.com");
        when(repo.findById(62L)).thenReturn(Optional.of(url));

        Optional<ShortUrl> result = service.findByCode("10");
        assertTrue(result.isPresent());
        assertEquals("https://google.com", result.get().getOriginalUrl());
        verify(repo).findById(62L);
    }

    @Test
    void find_by_id_should_throw_exception_for_invalid_shortcode() {
        assertThrows(
                IllegalArgumentException.class,
                () -> service.findByCode("@@@")
        );
        verify(repo, never()).findById(anyLong());
    }

    @Test

    void find_all_should_return_all_urls_in_db() {
        List<ShortUrl> urls = List.of(
                new ShortUrl("https://google.com"),
                new ShortUrl("https://github.com")
        );

        when(repo.findAll()).thenReturn(urls);
        List<ShortUrl> result = service.findAll();
        assertEquals(2, result.size());
        assertEquals(urls, result);
        verify(repo).findAll();
    }

    @Test
    void find_all_should_return_empty_list_when_no_urls_are_present_in_db() {
        when(repo.findAll()).thenReturn(List.of());
        List<ShortUrl> result = service.findAll();
        assertTrue(result.isEmpty());
        verify(repo).findAll();
    }
}