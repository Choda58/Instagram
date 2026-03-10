# -------- Build Stage --------
FROM eclipse-temurin:25-jdk AS build

RUN apt-get update && apt-get install -y maven

WORKDIR /build

COPY pom.xml .
RUN mvn -B dependency:go-offline

COPY src ./src

RUN mvn clean package -DskipTests

# -------- Runtime Stage --------
FROM eclipse-temurin:25-jdk

WORKDIR /app

COPY --from=build /build/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]