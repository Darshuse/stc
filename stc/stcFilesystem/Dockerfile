FROM openjdk:8
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} stcFilesystem.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/stcFilesystem.jar"]