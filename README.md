# URL Shortener - Spring Boot + JDBC + SQLite

## Project Overview

This project is a production style backend URL shortener built using Spring Boot, JdbcTemplate and SQLite.
It converts long URLs into short Base62 codes and redirects users efficiently using database lookup.

The goal of this project was to deeply understand backend fundamentals such as:

- Database design
- REST API development
- JDBC instead of ORM
- System design thinking
- Clean architecture

## Architecture

Client → Spring Boot API → SQLite Database → Redirect

Flow

- User sends long URL.
- URL stored in database.
- Auto‑increment ID generated.
- ID converted to Base62 short code.
- Short code saved in database.
- When user visits short URL → database lookup → redirect.

## Tech Stack

- Language: Java 21
- Framework: Spring Boot 3.x
- Database: SQLite
- Data Access: JdbcTemplate (No Hibernate)
- Build Tool: Maven
- Testing: JUnit (Base62 utility tests)

## API Endpoints

### Short code generation

```curl
## Request

curl --location 'http://localhost:8080/api/shorten' \
--header 'Content-Type: application/json' \
--data '{
    "url":"https://mail.google.com/mail/u/0/#inbox/FMfcgzQfBshlFrdnRvwzBWhBcShfLXPC"
}'

## Response

{
    "shortUrl": "http://localhost:8080/d"
}

```

### Short Code Redirect

Paste the short URL in browser. The browser will redirect to your original URL

```curl

GET http://localhost:8080/d

```

## How to Run

### Prerequisites

- Java 21
- Maven

### Steps

```bash
git clone https://github.com/abhijeet1069/urlshortner.git
cd urlshortner
mvn spring-boot:run
```
Once the application is running, generate your short urls. No DB setup required.