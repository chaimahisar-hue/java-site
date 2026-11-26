FROM maven:3.9-eclipse-temurin-17 AS build
COPY . .
RUN mvn clean package

FROM eclipse-temurin:17-jre
COPY --from=build /target/my-java-site-1.0.jar app.jar
CMD ["java", "-jar", "app.jar"]
