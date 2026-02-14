package com.backend.urlshortner.repository;

import com.backend.urlshortner.model.ShortUrl;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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

    public long insertUrl(String originalUrl){
        final String sql = "INSERT INTO urls(original_url) VALUES (?)";
        jdbcTemplate.update(sql, originalUrl);
        // SQLite specific: get last inserted id
        Long id = jdbcTemplate.queryForObject(
                "SELECT last_insert_rowid()",
                Long.class
        );
        if (id == null) {
            throw new RuntimeException("Failed to fetch last inserted id");
        }
        return id;
    }

    public void updateShortCode(long id, String shortCode) {
        final String sql = "UPDATE urls SET short_code = ? WHERE id = ?";
        jdbcTemplate.update(sql, shortCode, id);
    }

    public Optional<ShortUrl> findByShortCode(String shortCode) {
        String sql = """
                SELECT id, short_code, original_url
                FROM urls
                WHERE short_code = ?
                """;

        return jdbcTemplate.query(sql, mapper, shortCode)
                .stream()
                .findFirst();
    }

    public Optional<ShortUrl> findById(long id) {

        String sql = """
                SELECT id, short_code, original_url
                FROM urls
                WHERE id = ?
                """;

        return jdbcTemplate.query(sql, mapper, id)
                .stream()
                .findFirst();
    }

    public List<ShortUrl> findAll() {
        final String sql = "SELECT id, short_code, original_url FROM urls ORDER BY id";
        return jdbcTemplate.query(sql, mapper);
    }
}
