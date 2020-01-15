FROM maven:3.5.2-jdk-8-alpine AS MAVEN_BUILD
MAINTAINER Tarun Dhraik
COPY pom.xml /build/
COPY src /build/src/
WORKDIR /build/
RUN mvn package
FROM java:8
WORKDIR /app
COPY --from=MAVEN_BUILD /build/target/repayment-plan-generator-1.0.jar /app/
ENTRYPOINT ["java", "-jar", "repayment-plan-generator-1.0.jar"]