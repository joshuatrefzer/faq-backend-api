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





#FOR PRODUCTION using "docker-compose" not "docker compose" 

# DB- Data persists
prod-up:
	docker-compose up --build --force-recreate -d

# Stop containers, but keep volumes 
prod-down:
	docker-compose down --remove-orphans

# Stops everything and deletes volumes
prod-reset-db:
	docker-compose down --volumes --remove-orphans

# complete rebuild without loosing data
rebuild-prod:
	mvn clean install -DskipTests && \
	docker-compose down --remove-orphans && \
	docker-compose up --build --force-recreate -d

# rebuild backend container
prod-rebuild-backend:
	mvn clean install -DskipTests && \
	docker-compose up --build --force-recreate backend


# deletes everything, starts with empty DB
start-from-zero:
	docker compose down --volumes --remove-orphans && \
	mvn clean && \
	mvn install -DskipTests && \
	docker compose up --build --force-recreate