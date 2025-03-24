# DB- Data persists
dev-up:
	docker compose up --build --force-recreate -d

# Stop containers, but keep volumes 
dev-down:
	docker compose down --remove-orphans

# Stops everything and deletes volumes
reset-db:
	docker compose down --volumes --remove-orphans

# complete rebuild without loosing data
rebuild-dev:
	mvn clean install -DskipTests && \
	docker compose down --remove-orphans && \
	docker compose up --build --force-recreate -d

# rebuild backend container
rebuild-backend:
	mvn clean install -DskipTests && \
	docker compose up --build --force-recreate backend


#production commands
prod-up:
	docker compose -f docker-compose.prod.yml up --build -d

prod-down:
	docker compose -f docker-compose.prod.yml down --remove-orphans


# deletes everything, starts with empty DB
start-from-zero:
	docker compose down --volumes --remove-orphans && \
	mvn clean && \
	mvn install -DskipTests && \
	docker compose up --build --force-recreate