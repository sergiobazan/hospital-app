FROM maven:3.9.4-eclipse-temurin-21 as builder

WORKDIR /app

COPY mvnw pom.xml ./

COPY .mvn/ .mvn

RUN chmod +x mvnw

RUN ./mvnw dependency:go-offline

COPY src ./src

RUN ./mvnw clean install -DskipTests

FROM eclipse-temurin:21.0.2_13-jre-jammy as runner

EXPOSE 8080

WORKDIR /app

COPY --from=builder /app/target/*.jar /app/app.jar

ENTRYPOINT ["java", "-jar", "/app/app.jar"]