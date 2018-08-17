docker-compose up -d -f ./Docker/Postgres/docker-compose.yaml
docker-compose up -d -f ./Docker/Redis/docker-compose.yaml
docker-compose up -d -f ./Docker/Spark/docker-compose.yaml

docker ps

sbt "project api" "run"
sbt api/run