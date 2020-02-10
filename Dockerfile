FROM openjdk:11.0.2-jdk-slim-stretch

WORKDIR workspace

COPY build/libs/code-generator-0.0.1-SNAPSHOT.jar .

EXPOSE 8080

CMD ["java", "-Xmx1024m", "-jar", "code-generator-0.0.1-SNAPSHOT.jar"]