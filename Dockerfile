FROM gradle:6.3.0-jdk11 AS build

WORKDIR /home/gradle/facemash-remake

COPY --chown=gradle:gradle . .

RUN apt update -y
RUN apt install -y nodejs npm
RUN npm install -y
RUN npm rebuild node-sass
RUN npm run dev
RUN gradle bootJar


FROM openjdk:11

WORKDIR /usr/src/facemash-remake

COPY --from=build /home/gradle/facemash-remake/build/libs/facemash-app.jar .

RUN apt update -y
ENTRYPOINT ["java", "-jar", "facemash-app.jar"]