FROM openjdk:11-jdk-oracle
VOLUME /tmp
COPY build/libs/pos-0.0.1-SNAPSHOT.jar payhere.jar
ENTRYPOINT ["java","-jar","/payhere.jar"]