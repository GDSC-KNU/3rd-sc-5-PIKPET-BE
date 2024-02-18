FROM openjdk:17
ARG JAR_FILE=build/libs/*.jar
COPY $JAR_FILE app.jar
COPY build/libs/soc.json /app/src/main/resources/soc.json
ENTRYPOINT ["java","-jar","/app.jar"]