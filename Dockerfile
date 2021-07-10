FROM openjdk:8-jdk-alpine
COPY target/credit-card-processor-1.0.0.jar credit-processor.jar
ENTRYPOINT ["java","-jar","/credit-processor.jar"]