trap ctrl_c INT

function ctrl_c() {
  docker compose --file src/test/resources/conf/docker-compose-test.yml down
}

docker compose --file src/test/resources/conf/docker-compose-test.yml up --build --force-recreate