FROM openjdk:17
ARG JAR_FILE=build/libs/*.jar
COPY $JAR_FILE app.jar
COPY soc.json /src/main/resources/soc.json
ENTRYPOINT ["java","-jar","/app.jar"]