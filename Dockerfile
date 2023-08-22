FROM openjdk:19

COPY target/socialmedia-be-0.0.1-SNAPSHOT.jar /usr/src/socialmedia-be-0.0.1-SNAPSHOT.jar

WORKDIR /usr/src

CMD ["java", "-jar", "socialmedia-be-0.0.1-SNAPSHOT.jar"]
