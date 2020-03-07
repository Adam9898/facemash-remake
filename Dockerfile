FROM openjdk:11

ENV database.username=user database.password=facemash

EXPOSE 8080

ARG JAR_FILE=build/libs/facemash-app-0.0.1-SNAPSHOT.jar

ADD ${JAR_FILE} facemash-app.jar

ENTRYPOINT ["java","-jar","/facemash-app.jar"]