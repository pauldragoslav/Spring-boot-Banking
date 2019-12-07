FROM adoptopenjdk/openjdk11:jre-11.0.5_10-alpine
VOLUME /tmp
ARG JAR_FILE
COPY target/Banking-*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]