# Build container
FROM gradle:jre15 AS build

COPY ./ ./

RUN gradle clean \
    # && java -jar build/libs/gs-spring-boot-docker-0.1.0.jar
    && gradle build -x test

# Runtime environment container
FROM gradle:jre15

COPY --from=build ./home/gradle/build/libs/*.jar /api.jar

ENTRYPOINT ["java", "-jar", "/api.jar"]
