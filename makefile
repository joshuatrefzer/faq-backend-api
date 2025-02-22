rebuild:
	docker compose down && mvn clean && mvn install && docker compose up --build
