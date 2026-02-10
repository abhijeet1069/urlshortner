CREATE TABLE IF NOT EXISTS urls (
                                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                                    short_code TEXT NOT NULL UNIQUE,
                                    original_url TEXT NOT NULL,
                                    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                                    expires_at DATETIME,
                                    click_count INTEGER DEFAULT 0
);

CREATE INDEX IF NOT EXISTS idx_short_code ON urls(short_code);