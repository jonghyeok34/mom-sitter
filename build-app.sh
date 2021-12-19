# ./gradlew bootJar
docker build -t company-app:production .
docker rm -f company-app
docker run --name company-app -p 8080:8080 -e 'SPRING_PROFILES_ACTIVE=production' --link company-mysql:db -d company-app:production
docker logs -f company-app