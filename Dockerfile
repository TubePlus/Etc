#FROM openjdk:17-alpine AS builder
#COPY gradlew .
#COPY gradle gradle
#COPY build.gradle .
#COPY settings.gradle .
#COPY src src
#RUN chmod +x ./gradlew
#RUN ./gradlew bootJAR

FROM openjdk:17-alpine
COPY ${JAR_FILE} app.jar
CMD ["java", "-jar", "app.jar"]


#FROM openjdk:17-alpine
#COPY --from=builder build/libs/*.jar app.jar
#EXPOSE 8004
#ENTRYPOINT ["java", "-Duser.timezone=Asia/Seoul", "-Dspring.profiles.active=prod","-jar", "/app.jar"]