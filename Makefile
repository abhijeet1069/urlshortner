APP_NAME=myapp
CONTAINER_NAME=myapp-container

build-jar:
	mvn clean package

build:
	docker build -t $(APP_NAME) .

run:
	docker run -d \
	-p 8080:8080 \
	-v $(PWD)/data:/data \
	--name $(CONTAINER_NAME) \
	$(APP_NAME)

stop:
	docker stop $(CONTAINER_NAME)

remove:
	docker rm $(CONTAINER_NAME)

logs:
	docker logs -f $(CONTAINER_NAME)

rebuild: build-jar build stop remove run

clean:
	docker rmi $(APP_NAME)