FROM maven:3.8.6-jdk-11-slim AS build
COPY /src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean package

FROM gcr.io/distroless/java
COPY --from=build /usr/src/app/target/KnightBoard-0.0.1-SNAPSHOT.jar KnightBoard-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/KnightBoard-0.0.1-SNAPSHOT.jar"]
