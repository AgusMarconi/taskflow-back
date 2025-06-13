# Etapa de build
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa de runtime
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Puerto por defecto para Spring Boot
EXPOSE 8080

# Variable de entorno para el puerto (Vercel puede inyectar PORT)
ENV PORT=8080

ENTRYPOINT ["java", "-jar", "app.jar"]
