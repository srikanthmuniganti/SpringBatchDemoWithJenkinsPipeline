#FROM openjdk:8-jdk-alpine
#COPY target/SpringMysqlDockerDemo-0.0.1-SNAPSHOT.jar .
#ADD target/spring-batch-demo.jar spring-batch-demo.jar 
#EXPOSE 8090
#ENTRYPOINT ["java", "-jar", "spring-batch-demo.jar"]


FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 8000
ADD target/*.jar app.jar
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]