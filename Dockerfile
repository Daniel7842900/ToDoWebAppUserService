# ==== Multi-stage builds ====

# ==== Stage 0 ====
FROM maven:3.6.1-jdk-8-slim AS build
RUN mkdir -p /api/user
WORKDIR /api/user
COPY pom.xml /api/user
COPY src /api/user/src
RUN mvn -f pom.xml clean package
# ==== Stage 0 end ====

# ==== Stage 1 ====
FROM openjdk:8-alpine
COPY --from=build /api/user/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
# ==== Stage 1 end ====