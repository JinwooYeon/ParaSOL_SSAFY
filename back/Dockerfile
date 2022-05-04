#FROM openjdk:11 as builder
FROM gradle:7.4.1-jdk11 as builder
WORKDIR /app
COPY ./gradlew .
COPY ./gradle gradle
COPY ./build.gradle .
COPY ./settings.gradle .
COPY ./src src
#RUN chmod +x gradlew
RUN gradle -b ./build.gradle bootJar

FROM openjdk:11-jre-slim
COPY --from=builder /app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]