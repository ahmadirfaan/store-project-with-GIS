FROM adoptopenjdk/openjdk11:alpine-slim

ENV DB_HOST=172.17.0.1
ENV DATA_DIR=/images-store

RUN addgroup -S spring && adduser -S spring -G spring
RUN mkdir -p $DATA_DIR
RUN chown spring $DATA_DIR

USER spring:spring
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]