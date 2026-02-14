package com.backend.urlshortner.repository;

import com.backend.urlshortner.model.ShortUrl;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UrlRepository {
    private final JdbcTemplate jdbcTemplate;

    public UrlRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final RowMapper<ShortUrl> mapper = (rs, rowNum) ->
            new ShortUrl(
                    rs.getLong("id"),
                    rs.getString("short_code"),
                    rs.getString("original_url")
            );

    public List<ShortUrl> findAll() {
        String sql = "SELECT id, short_code, original_url FROM urls ORDER BY id";
        return jdbcTemplate.query(sql, mapper);
    }
}
