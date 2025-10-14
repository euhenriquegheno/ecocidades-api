FROM openjdk:17-jdk-slim as builder
WORKDIR /app
COPY mvnw ./
COPY .mvn .mvn
COPY pom.xml ./
COPY src ./src
RUN ./mvnw clean package -DskipTests

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
ENV PROFILE=prd
EXPOSE 8080
ENTRYPOINT ["java", "-Dspring.profile.actives=${PROFILE}", "-jar", "app.jar"]

