# URL Shortener – Backend Feature Checklist

This document lists the features required to turn a basic URL shortener into a **solid, backend-focused project**.  
Features are grouped by priority, from **core functionality** to **professional polish**.

---

## Tier 1 — Core Features (Non-Negotiable)

These features define the **minimum viable backend system**.

### 1. URL Shortening
- Endpoint to accept a long URL
- Generate and return a short URL
- Validate input URL format
- Reject malformed or unsupported URLs

### 2. Redirect Handling
- Redirect short URL to original URL
- Correct HTTP status code:
  - Decide between `301` (permanent) or `302` (temporary)
- Handle error cases:
  - Invalid code → `404 Not Found`
  - Expired link → `410 Gone`

### 3. Short Code Generation
- Deterministic short-code generation strategy
- Guaranteed uniqueness
- Collision handling (retry or regenerate)
- Database-level uniqueness constraint

### 4. Persistence
- Store mappings in a database
- Indexed lookup on `short_code`
- Clear schema design
- No in-memory-only storage

> Completing Tier 1 already puts this project ahead of most demo-level repositories.

---

## Tier 2 — Backend Depth (Strong Project)

These features demonstrate **real backend engineering skills**.

### 5. Expiration Support
- Optional expiration time per short URL
- Automatic invalidation after expiry
- Proper HTTP response (`410 Gone`)

### 6. Click Analytics
- Track number of redirects
- Store last-accessed timestamp
- Atomic updates to avoid lost increments

### 7. Concurrency Correctness
- Handle multiple simultaneous requests
- Avoid race conditions
- Proper transactional behavior

### 8. Error Handling Strategy
- Centralized exception handling
- Consistent error response format
- No stack traces exposed to clients

### 9. Input Validation
- URL length limits
- Scheme validation (`http`, `https` only)
- Reject unsafe or unsupported schemes

### 10. Testing
- Unit tests for service logic
- Integration tests for controllers
- Redirect behavior tested explicitly

> Tier 1 + Tier 2 = **solid backend project**.

---

## Tier 3 — Professional Polish (Portfolio Level)

These features signal **engineering maturity**.

### 11. Short Code Strategy (Explainable)
Choose and justify one:
- Base62 encoding of numeric ID
- Hash-based approach with collision resolution
- Sequence-based generation

Document the trade-offs.

### 12. Rate Limiting
- Prevent abuse of shortening endpoint
- Simple implementation is sufficient
- Demonstrates traffic-awareness

### 13. Logging
- Structured logging
- Log redirects and failures
- Optional request correlation ID

### 14. Configuration Management
- Configurable base URL
- Default expiration via configuration
- Environment profiles (`dev`, `prod`)

### 15. Clean API Design
- Versioned APIs (`/api/v1`)
- Clear request/response DTOs
- No database details exposed

---

## Tier 4 — Optional (Advanced / Bonus)

Only attempt after completing previous tiers.

- Database migrations (Flyway or Liquibase)
- Soft deletes
- Admin APIs (list, disable URLs)
- Dockerfile
- Comprehensive README:
  - architecture
  - design decisions
  - scaling considerations

---

## Completion Criteria

This project is considered **complete and solid** if:

- It handles invalid input gracefully
- It behaves correctly under concurrent access
- It includes meaningful tests
- Design decisions are documented
- It runs with a single command

---

## Goal

This project is designed to demonstrate:
- Backend fundamentals
- Correctness over hype
- Thoughtful design decisions
- Production-oriented thinking