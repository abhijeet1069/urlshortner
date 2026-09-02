.PHONY: build up down restart logs ps

build:
	docker compose up -d --build

up:
	docker compose up -d

down:
	docker compose down

restart:
	docker compose restart

# Follow logs
logs:
	docker compose logs -f

# Show service status
ps:
	docker compose ps