FROM openjdk:8-jdk-alpine
#COPY target/SpringMysqlDockerDemo-0.0.1-SNAPSHOT.jar .
ADD target/spring-batch-demo.jar spring-batch-demo.jar 
EXPOSE 8090
ENTRYPOINT ["java", "-jar", "spring-batch-demo.jar"]