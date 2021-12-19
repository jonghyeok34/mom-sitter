FROM adoptopenjdk/openjdk11:alpine
COPY jars/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]