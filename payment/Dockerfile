FROM openjdk:17

WORKDIR /app

COPY ./build/libs/*SNAPSHOT.jar /app/app.jar

EXPOSE 8084

CMD ["java", "-jar", "/app/app.jar"]
