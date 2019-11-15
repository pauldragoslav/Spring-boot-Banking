FROM adoptopenjdk/openjdk11:jdk-11.0.5_10
VOLUME /tmp
ARG JAR_FILE
COPY target/Banking-*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]