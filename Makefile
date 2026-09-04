.PHONY: build up down restart logs ps

build:
	docker compose up --build

up:
	docker compose up --build

down:
	docker compose down

restart: down up

# Follow logs
logs:
	docker compose logs -f

# Show service status
ps:
	docker compose ps