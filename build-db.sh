docker build -t company-mysql:local db/.
docker rm -f company-mysql
docker run --name company-mysql --env-file db/db.conf -p 3306:3306 -d company-mysql:local