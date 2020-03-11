FROM gradle:jdk11

#env variables for the spring app
#ENV database_username=user database_password=facemash

EXPOSE 8080

#COPY ../build/jars.jar /home/facemash-remake

WORKDIR /home/facemash-remake

ENTRYPOINT["java", "build/classes/java/main/com/facemash/FacemashApplication.class"]