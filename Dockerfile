# 소스코드 Build Stage
FROM maven:3.9.7-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn package -DskipTests

# 실행 Stage
FROM eclipse-temurin:21.0.2_13-jdk
WORKDIR /app
COPY --from=build /app/target/awsUser-0.0.1-SNAPSHOT.jar ./app.jar
CMD ["java", "-jar", "app.jar"]