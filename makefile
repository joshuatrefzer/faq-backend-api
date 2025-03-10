rebuild:
	docker compose down && mvn clean && mvn install && docker compose up --build

rebuild-dev:
	docker compose down --volumes --remove-orphans && \
	mvn clean && \
	mvn install -DskipTests && \
	docker compose up --build --force-recreate