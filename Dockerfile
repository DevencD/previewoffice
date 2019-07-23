FROM java:8
VOLUME /tmp
ADD previewoffice-0.0.8-SNAPSHOT.jar /app.jar
ENTRYPOINT ["java","-jar","/app.jar"]