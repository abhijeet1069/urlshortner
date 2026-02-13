package com.backend.urlshortner.repository;

import com.backend.urlshortner.model.ShortUrl;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UrlRepository {
    private final JdbcTemplate jdbcTemplate;

    public UrlRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<ShortUrl> rowMapper = (rs,rowNum) ->
            new ShortUrl(
                    rs.getLong("id"),
                    rs.getString("short_code"),
                    rs.getString("original_url"),
                    rs.getInt("click_count")
            );
}
