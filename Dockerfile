FROM openjdk:17-oracle
ADD target/car-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]
