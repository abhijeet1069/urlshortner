.PHONY: build up down restart logs ps

build:
	docker compose up --build

up:
	docker compose up --build

down:
	docker compose down

restart: down up

# Show service status
ps:
	docker compose ps

# test and generate HTML report
test:
	mvn test
	mvn surefire-report:report