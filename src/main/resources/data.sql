INSERT INTO urls(original_url, short_code)
VALUES ('www.example.com', '1')
ON CONFLICT (short_code) DO NOTHING;